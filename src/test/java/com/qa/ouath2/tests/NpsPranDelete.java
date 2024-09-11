package com.qa.ouath2.tests;

import com.qa.ouath2.api.StatusCode;
import com.qa.ouath2.api.applicationApi.PlaylistApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.qa.ouath2.api.Route.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class NpsPranDelete {

    @Test(priority = 1)
    public void deletePran(){

        Response response= PlaylistApi.DELETE(API +  NPS + DELETE,"");

        assertStatusCode(response.statusCode(), StatusCode.CODE_200);

    }
    public void assertStatusCode(int  actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode,equalTo(statusCode.code));
    }
}
