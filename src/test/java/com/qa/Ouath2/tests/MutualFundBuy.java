package com.qa.Ouath2.tests;

import com.spotify.ouath2.api.StatusCode;
import com.spotify.ouath2.api.applicationApi.PlaylistApi;
import com.spotify.ouath2.pojo.Playlist;
import com.spotify.ouath2.utils.DataLoader;
import com.spotify.ouath2.utils.PropertyManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

import static com.spotify.ouath2.api.Route.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MutualFundBuy {
    static String purchase_id = DataLoader.getInstance().getMfPurchaseId();
    static int old_id;

    @Test(priority = 1)
    public void getOldId() throws IOException {
        Response response = PlaylistApi.get(BASE_PATH + MF_PURCHASE + "/" + purchase_id, "");
        old_id = response.path("old_id");

    }

    @Test(priority = 2)
    public void mfPurchaseSubmit() throws IOException {
        Playlist requestPlaylist = playlistBuilder(old_id, "SUBMITTED");
        Response response = PlaylistApi.post(requestPlaylist, API + OMS + SIMULATE + ORDERS + "/" + old_id);
        Response response1 = PlaylistApi.get(BASE_PATH + MF_PURCHASE + "/" + purchase_id, "");
        String state = response1.path("state");
        Assert.assertEquals("submitted", state);

        assertStatusCode(response.statusCode(), StatusCode.CODE_200);

    }

    @Test(priority = 3)
    public void mfPurchaseSuccess() throws IOException {
        Properties props = new PropertyManager().getProps();
        Playlist requestPlaylist = playlistBuilder(old_id, "SUCCESSFUL");
        Response response = PlaylistApi.post(requestPlaylist, API + OMS + SIMULATE + ORDERS + "/" + old_id);

        Response response2 = PlaylistApi.get(BASE_PATH + MF_PURCHASE + "/" + purchase_id, "");
        String state = response2.path("state");
//        Assert.assertEquals("successful", state);

        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }

    public Playlist playlistBuilder(int amcOldId, String status) {
        return Playlist.builder().
                amcOrderId(amcOldId).
                status(status).
                build();
    }

    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist) {
        assertThat(responsePlaylist.getAmcOrderId(), equalTo(requestPlaylist.getAmcOrderId()));
    }

    public void assertStatusCode(int actualStatusCode, StatusCode statusCode) {
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }

}
