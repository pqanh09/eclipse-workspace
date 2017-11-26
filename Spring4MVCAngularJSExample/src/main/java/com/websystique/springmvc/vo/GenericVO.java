
package com.websystique.springmvc.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(  
        use = JsonTypeInfo.Id.NAME,  
        include = JsonTypeInfo.As.WRAPPER_OBJECT,  
        property = "type")  
@JsonSubTypes({     
    @Type(value=MangaVO.class, name="MangaVO")
//    @Type(value=AAAServerVO.class, name="AAAServerVO"),
}) 
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class GenericVO{	
    public abstract String retrieveNGObjectType();
}
