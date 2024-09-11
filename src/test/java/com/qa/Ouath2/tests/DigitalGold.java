package com.qa.Ouath2.tests;

import com.spotify.ouath2.api.StatusCode;
import com.spotify.ouath2.api.applicationApi.PlaylistApi;
import com.spotify.ouath2.pojo.Playlist;
import com.spotify.ouath2.utils.DataLoader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.ouath2.api.Route.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DigitalGold {
    static String contact= DataLoader.getInstance().getContactNoForGold();

    @Test(priority = 1)
    public void ChangeTimeInterval(){
        Playlist requestPlaylist= playlistBuilder("nishath","","Khanum","changeinterval",contact);

        Response response= PlaylistApi.post(requestPlaylist, API + TESTING + DELETEDATA);

        assertStatusCode(response.statusCode(), StatusCode.CODE_200);

    }
    public Playlist playlistBuilder(String firstName,String middleName,String lastName,String type,String contact){
        return Playlist.builder().
                firstname(firstName).
                middlename(middleName).
                lastname(lastName).
                type(type).
                contact(contact).
                build();
    }
    public void assertStatusCode(int  actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode,equalTo(statusCode.code));
    }
}
