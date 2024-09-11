package com.qa.ouath2.api;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.joda.time.Instant;

import java.util.HashMap;
import java.util.Map;

import static com.qa.ouath2.api.Route.*;
import static com.qa.ouath2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {

    static String access_token;
    static Instant expire_time;
    public static String getToken(){
        try{
            if(access_token==null || Instant.now().isAfter(expire_time)){
                Response response=renewToken();
                access_token=response.path("access_token");
                expire_time=Instant.now().plus(720000-300);
            }
            else {
                System.out.println("Token is good to use");
            }
        }
        catch (Exception e){
            throw new RuntimeException("ABORT !!! Renew Token Failed");
        }
        return access_token;
    }
    public static String getMobileToken(String mobileNo){
        try{
            if(access_token==null || Instant.now().isAfter(expire_time)){
                Response response=renewMobileToken(mobileNo);
                access_token=response.path("token");
                expire_time=Instant.now().plus(720000-300);
            }
            else {
                System.out.println("Token is good to use");
            }
        }
        catch (Exception e){
            throw new RuntimeException("ABORT !!! Renew Token Failed");
        }
        return access_token;
    }
    private static Response renewToken(){
        HashMap<String,String> formParams=new HashMap<String,String>();
        formParams.put("email","amit@floatr.in");
        formParams.put("password","sbXTnJZ#KyPauq7sa");
        HashMap<String,String> headers=new HashMap<String,String>();
        headers.put("x-tenant-id","floatr");
        Header header=new Header("content-type","application/x-www-form-urlencoded");

        String body="{\n" +
                "    \"email\": \"amit@floatr.in\",\n" +
                "    \"password\": \"sbXTnJZ#KyPauq7sa\"\n" +
                "} ";

        Map<String, String> formParams1 = new HashMap<>();
        formParams1.put("client_id", "floatr_test_e4bc2f27ca5e7832df0d70668213b262");
        formParams1.put("client_secret", "h0arQZVQUa2q5SRiRbyCLUHJxEsO1NP7");
        formParams1.put("grant_type", "client_credentials");

        Response response=given().
                baseUri("https://s.finprim.com").
                header("content-type", "application/x-www-form-urlencoded").
                header("accept", "application/json").
                formParams(formParams1).
                when().post(BASE_PATH + AUTH + FLOATR + TOKEN).then().spec(getResponseSpec()).extract().response();

        if(response.statusCode() !=200){
            throw new RuntimeException("ABORT !!! Renew Token Failed");
        }
        return response;
    }
    public static Response renewMobileToken(String mobileNo){
        String body="{  \n" +
                "    \"type\" :\"token\",\n" +
                "     \"contact\":\""+mobileNo+"\",\n" +
                "     \"pan\":\"CBFJG5746H\",\n" +
                "     \"account_number\":\"3553533\",\n" +
                "     \"account_type\": \"Personal\",\n" +
                "     \"firstname\": \"Nishath\",\n" +
                "      \"middlename\": \"Khanum\",\n" +
                "      \"lastname\": \"S\"\n" +
                "}";
        Response response=given().
                baseUri("https://dev.floatr.in").
                contentType("application/json").body(body).
                when().post(API + TESTING  + DELETEDATA).then().spec(getResponseSpec()).extract().response();

        if(response.statusCode() !=200){
            throw new RuntimeException("ABORT !!! Renew Token Failed");
        }
        return response;
    }
}
