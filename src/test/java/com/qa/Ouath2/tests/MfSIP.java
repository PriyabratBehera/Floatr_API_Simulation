package com.qa.Ouath2.tests;

import com.spotify.ouath2.api.StatusCode;
import com.spotify.ouath2.api.applicationApi.PlaylistApi;
import com.spotify.ouath2.pojo.Playlist;
import com.spotify.ouath2.utils.DataLoader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.ouath2.api.Route.*;
import static com.spotify.ouath2.api.Route.ORDERS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MfSIP {

    static String sip_id= DataLoader.getInstance().getMfSipID();
    static int old_id;
    @Test(priority = 1)
    public void getSipOldId(){
        Playlist requestPlaylist= playlistBuilder(sip_id);

        Response response= PlaylistApi.post(requestPlaylist, BASE_PATH + MF_PURCHASE);
        old_id=response.path("old_id");

//        assertStatusCode(response.statusCode(), StatusCode.CODE_200);

    }
    @Test(priority = 2)
    public void changeSipStatus(){
        Playlist requestPlaylist= playlistBuilderForStatus("SUBMITTED");

        Response response= PlaylistApi.post(requestPlaylist,API + OMS + SIMULATE + ORDERS +"/"+old_id);
//        String message=response.path("message");
//        Assert.assertEquals("order updated successfully",message);
//
//        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }
    @Test(priority = 3)
    public void increaseInstallments(){
        Playlist requestPlaylist=playlistBuilderForStatus("SUCCESSFUL");

        Response response= PlaylistApi.post(requestPlaylist,API + OMS + SIMULATE + ORDERS +"/"+old_id);
//        String message=response.path("message");
//        Assert.assertEquals("order updated successfully",message);
//
//        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }
    public Playlist playlistBuilder(String sipId){
        return Playlist.builder().
                plan(sipId).
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
