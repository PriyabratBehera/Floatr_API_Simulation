package com.qa.ouath2.tests;

import com.qa.ouath2.api.StatusCode;
import com.qa.ouath2.api.applicationApi.PlaylistApi;
import com.qa.ouath2.pojo.Playlist;
import com.qa.ouath2.utils.DataLoader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.qa.ouath2.api.Route.*;
import static com.qa.ouath2.api.Route.MF_REDEMPTIONS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MfSWP {

    static String mf_swp_id= DataLoader.getInstance().getMfSwpId();
    static int old_id;
    @Test(priority = 1)
    public void getSWPOldId(){
        Playlist requestPlaylist= playlistBuilder(mf_swp_id);
        Response response= PlaylistApi.post(requestPlaylist, BASE_PATH + MF_REDEMPTIONS);
        old_id=response.path("old_id");

        assertStatusCode(response.statusCode(), StatusCode.CODE_200);

    }
    @Test(priority = 2)
    public void checkSWPStatus(){
        Response response= PlaylistApi.get(BASE_PATH + MF_REDEMPTIONS,"plan="+mf_swp_id);

        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
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
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }


}
