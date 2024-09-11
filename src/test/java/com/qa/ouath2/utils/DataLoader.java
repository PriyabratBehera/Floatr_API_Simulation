package com.qa.ouath2.utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;


    private DataLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance(){
        if(dataLoader==null){
            dataLoader=new DataLoader();
        }
        return dataLoader;
    }

    public String getMfSipID(){
        String prop=properties.getProperty("mf_sip_id");
        if(prop != null) return prop;
        else throw new RuntimeException("Property mf_sip_id is not specified in the config.properties file");
    }
    public String getMfPurchaseId(){
        String prop=properties.getProperty("mf_purchase_id");
        if(prop != null) return prop;
        else throw new RuntimeException("Property mf_purchase_id is not specified in the config.properties file");
    }
    public String getMfRedemptionId(){
        String prop=properties.getProperty("mf_redemption_id");
        if(prop != null) return prop;
        else throw new RuntimeException("Property mf_redemption_id is not specified in the config.properties file");
    }
    public String getMfStpId(){
        String prop=properties.getProperty("mf_stp_id");
        if(prop != null) return prop;
        else throw new RuntimeException("Property mf_stp_id is not specified in the config.properties file");
    }
    public String getMfSwpId(){
        String prop=properties.getProperty("mf_swp_id");
        if(prop != null) return prop;
        else throw new RuntimeException("Property mf_swp_id is not specified in the config.properties file");
    }
    public String getContactNoForGold(){
        String prop=properties.getProperty("contact_no");
        if(prop != null) return prop;
        else throw new RuntimeException("Property contact_no is not specified in the config.properties file");
    }
    public String getMobileNoForToken(){
        String prop=properties.getProperty("user_mobile_number");
        if(prop != null) return prop;
        else throw new RuntimeException("Property user_mobile_number is not specified in the config.properties file");
    }
}
