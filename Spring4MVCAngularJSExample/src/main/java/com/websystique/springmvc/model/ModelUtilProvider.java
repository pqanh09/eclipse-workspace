package com.websystique.springmvc.model;

public class ModelUtilProvider {
    private static ModelUtil modelUtil = null;
    
    public static ModelUtil getModelUtil() {
        if (modelUtil == null) {
            setModelUitl(new ModelUtil());
        }
        return modelUtil;
    }

    public static void setModelUitl(ModelUtil modelUtil) {
        ModelUtilProvider.modelUtil = modelUtil;
    }
}
