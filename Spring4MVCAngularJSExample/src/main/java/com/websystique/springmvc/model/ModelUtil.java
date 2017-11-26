package com.websystique.springmvc.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

public class ModelUtil {
	private static final String NO_SUCH_INSTANCE = "Not Avaliable";
    /**
     * This method is used to create an object from a map of attribute-value, providing the type of the object.
     * If the map has more attributes then the fields of the class, redundant attribute of the map will be ignored.
     * If the map has less attributes then the fields of the class, the remaining fields of the class will have value null or its default value
     * 
     * @param attributes - a map of attribute-value which will be used to create an object
     * @param entityClass - the object representing Class type of the creating object
     * @return the instance of type of entityClass
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> T toObject(Map<String, Object> attributes, Class<T> entityClass) throws Exception{
        T entity = entityClass.newInstance();
        for (Method method : entityClass.getMethods()) {
            if (method.getName().startsWith("set")) {
                Object valueToSet = attributes.get(getNameFromGetterSetter(method.getName()));
                
                // this check is for the case the field is declared in primitive type.
                // It is recommended not to use primitive type if possible, use wrapper type instead
                if (valueToSet != null) {
                    if (method.getParameterTypes()[0].isEnum()) {
                        valueToSet = Enum.valueOf((Class<? extends Enum>) method.getParameterTypes()[0], String.valueOf(valueToSet));
                    } else if (valueToSet instanceof Map<?, ?> && !method.getParameterTypes()[0].isAssignableFrom(Map.class)) {
                        valueToSet = toObject((Map) valueToSet, method.getParameterTypes()[0]);
                    }
                  //Deepak - Update to check (Not Available) is set any params other than String class
                	if (!method.getParameterTypes()[0].getTypeName().equals("java.lang.String"))
                	{
                		if (String.valueOf(valueToSet).equals(NO_SUCH_INSTANCE))
                			continue;
                	}
                	try{
                		method.invoke(entity, valueToSet);
                	}
                	catch(IllegalArgumentException e)
                	{
                		
                	}
                }
            }
        }
        return entity;
    }
    
    /**
     * This method is used to convert an object to a map of attribute-value
     * 
     * @param entity - the object which will be converted
     * @return a map of attribute-value
     * @throws Exception
     */
    public <T> Map<String, Object> toMap(T entity) throws Exception {
        Map<String, Object> attributes = new HashMap<String, Object>();
        for (Method method : entity.getClass().getMethods()) {
        	ModelMapping modelMapping = method.getAnnotation(ModelMapping.class);
        	boolean skipConverting = modelMapping != null ? modelMapping.skipConverting() : false;
            if (!skipConverting && method.getName().startsWith("get") && !method.getName().equals("getClass")) {
                Object value = method.invoke(entity);
                attributes.put(getNameFromGetterSetter(method.getName()), value);
            }
        }
        return attributes;
    }

    /**
     * This method is used to extract the attribute name from getter or setter
     * 
     * @param methodName - the method name of getter or setter
     * @return the attribute name
     */
    private String getNameFromGetterSetter(String methodName) {
        String attributeName = methodName.substring(3);
        String firstLetter = attributeName.substring(0, 1).toLowerCase();
        StringBuilder result = new StringBuilder(firstLetter).append(attributeName.substring(1));
        return result.toString();
    }

    /**
     * This method is used to get value of a field in a model object reflectively.
     * 
     * @param entity - the object which one of its attributes value will be got
     * @param fieldName - name of the field whose value will be got
     * @param entityClass - the object of Class type of the entity
     * @return the value of a field
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public <T> Object getFieldValue(T entity, String fieldName, Class<? extends Object> entityClass) throws NoSuchMethodException,
            SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String getter = createGetterSetterFromName(fieldName, "get");
        Method method = entityClass.getMethod(getter);
        Object value = method.invoke(entity);
        return value;
    }

    /**
     * This method is used to create a getter, setter method name from an attribute
     * 
     * @param attributeName - the attribute name from which the getter, setter will be crated
     * @param prefix - the prefix which will be concatenated to the attribute name. The possible value is get or set 
     * @return the getter, setter method name
     */
    private String createGetterSetterFromName(String attributeName, String prefix) {
        String firstLetter = attributeName.substring(0, 1).toUpperCase();
        StringBuilder result = new StringBuilder(prefix).append(firstLetter).append(attributeName.substring(1, attributeName.length()));
        return result.toString();
    }   
    

    
    public <SourceType, DestType> DestType convertTo(SourceType source, Class<DestType> destClazz){
        DestType rs = BeanUtils.instantiateClass(destClazz);
        BeanUtils.copyProperties(source, rs);
        return rs;
    }
    
    public <SourceType, DestType> DestType convertTo(Map<String, Object> source, Class<DestType> destClazz) throws Throwable{
        DestType rs = BeanUtils.instantiateClass(destClazz);
        org.apache.commons.beanutils.BeanUtils.populate(rs, source);
        return rs;
    }
    
    /**
     * Allow some properties to be ignored 
     * @param source
     * @param destClazz
     * @param ignoreProperties
     * @return
     * @throws BeanInstantiationException
     * @throws BeansException
     */
    public <SourceType, DestType> DestType convertTo(SourceType source, Class<DestType> destClazz, String... ignoreProperties) throws BeanInstantiationException, BeansException{
        DestType rs = BeanUtils.instantiateClass(destClazz);
        BeanUtils.copyProperties(source, rs, ignoreProperties);
        return rs;
    }
    
}
