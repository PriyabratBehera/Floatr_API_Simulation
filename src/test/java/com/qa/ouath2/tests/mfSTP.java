package com.qa.ouath2.tests;

import com.qa.ouath2.api.StatusCode;
import com.qa.ouath2.api.applicationApi.PlaylistApi;
import com.qa.ouath2.pojo.Playlist;
import com.qa.ouath2.utils.DataLoader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.qa.ouath2.api.Route.BASE_PATH;
import static com.qa.ouath2.api.Route.MF_SWITCHES;
import static com.qa.ouath2.api.Route.*;
import static com.qa.ouath2.api.Route.ORDERS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class mfSTP {
    static String mf_stp_id= DataLoader.getInstance().getMfStpId();
    static int old_id;
    @Test(priority = 1)
    public void getSWPOldId(){
        Playlist requestPlaylist= playlistBuilder(mf_stp_id);
        Response response= PlaylistApi.post(requestPlaylist, BASE_PATH + MF_SWITCHES);
        old_id=response.path("old_id");

        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }
    @Test(priority = 2)
    public void checkSTPStatus(){
        Response response= PlaylistApi.get(BASE_PATH + MF_SWITCH_PLANS+"/"+mf_stp_id,"");

        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        String frequency=response.path("frequency");
        String mobNo=response.path("consent.mobile");
        System.out.println("Installments frequency: "+frequency);
        System.out.println("Mobile number: "+mobNo);
    }

    @Test(priority = 3)
    public void updateSwpStatusASSubmit(){
        Playlist requestPlaylist= playlistBuilderForStatus("SUBMITTED");
        Response response= PlaylistApi.post(requestPlaylist,API + OMS + SIMULATE + ORDERS +"/"+old_id);

        assertStatusCode(response.statusCode(), StatusCode.CODE_200);

        String message="order updated successfully";
        Assert.assertEquals(message,response.path("message"));

    }
    @Test(priority = 4)
    public void updateSwpStatusASSuccess(){
        Playlist requestPlaylist= playlistBuilderForStatus("SUCCESSFUL");
        Response response= PlaylistApi.post(requestPlaylist,API + OMS + SIMULATE + ORDERS +"/"+old_id);

        assertStatusCode(response.statusCode(), StatusCode.CODE_200);

        String message="order updated successfully";
        Assert.assertEquals(message,response.path("message"));
    }
    public Playlist playlistBuilder(String stp_plan){
        return Playlist.builder().
                plan(stp_plan).
                build();
    }
    public Playlist playlistBuilderForStatus(String status){
        return Playlist.builder().
                status(status).
                build();
    }
    public void assertPlaylistEqual(Playlist responsePlaylist,Playlist requestPlaylist){
        assertThat(responsePlaylist.getAmcOrderId(),equalTo(requestPlaylist.getAmcOrderId()));
    }
    public void assertStatusCode(int  actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode,equalTo(statusCode.code));
    }

}
