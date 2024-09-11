package com.qa.ouath2.api.applicationApi;

import com.qa.ouath2.pojo.Playlist;
import io.restassured.response.Response;

import static com.qa.ouath2.api.SpecBuilder.getRequestSpec;
import static com.qa.ouath2.api.SpecBuilder.getResponseSpec;
import static com.qa.ouath2.api.TokenManager.getMobileToken;
import static com.qa.ouath2.api.TokenManager.getToken;
import static io.restassured.RestAssured.given;

public class PlaylistApi {
    public static Response get(String base_path,String queryParameter){
        return given(getRequestSpec()).
                auth().oauth2(getToken()).
//                header("Authorization","Bearer "+getToken()).
                when().get(base_path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }
    public static Response post(Playlist requestPlaylist,String base_path){
//        Playlist requestPlaylist1= new Playlist();
//        int str=requestPlaylist.getAmcOrderId();
        return given(getRequestSpec()).
                body(requestPlaylist).
                auth().oauth2(getToken()).
//                header("Authorization","Bearer "+getToken()).
                when().post(base_path).
                then().spec(getResponseSpec()).extract().
                response();
    }

    public static Response GET(String base_path,String queryParameter){
        return given(getRequestSpec()).
                auth().oauth2(getMobileToken("8865254755")).
//                header("Authorization",getMobileToken("8865254755")).
                when().get(base_path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }
    public static Response POST(Playlist requestPlaylist,String base_path){
        return given(getRequestSpec()).
                body(requestPlaylist).
                auth().oauth2(getMobileToken("8865254755")).
//                header("Authorization",getMobileToken("8865254755")).
                when().post(base_path).
                then().spec(getResponseSpec()).extract().
                response();
    }
    public static Response DELETE(String base_path,String queryParameter){
        return given(getRequestSpec()).
                auth().oauth2(getMobileToken("8865254755")).
//                header("Authorization",getMobileToken("8865254755")).
                when().delete(base_path).
                then().spec(getResponseSpec()).extract().
                response();
    }


}
