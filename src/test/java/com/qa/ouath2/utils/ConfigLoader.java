package com.qa.ouath2.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;


    private  ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/cofig.properties");

    }

    public static ConfigLoader getInstance(){
        if(configLoader==null){
            configLoader=new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientID(){
        String prop=properties.getProperty("client_id");
        if(prop != null) return prop;
        else throw new RuntimeException("Property client_id is not specified in the config.properties file");
    }
}
