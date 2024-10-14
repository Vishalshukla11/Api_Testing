package spotifyForDeveloper;

import static org.hamcrest.Matchers.oneOf;

import static io.restassured.RestAssured.given;
import org.junit.Test;

import io.restassured.response.Response;

public class MySpotify_AutoMation {
   private String access_Token = "BQDGrIt4bFPHaH-m-mGS9f6jip0gOHHksnxRlORl4nZccINyQEvuKICYvOuPQUj2-Y2S5RzhFNCGVys8TFMn_MJsOAMPPZs470UNamoKmBb_ZT1BD_fmmhkEkx84Z-su_aQz88hoqeVd7NEG7ARcbCWvgtESPkI9H0W5TKMLC5q_dg1yHB-h-MJnP-yN2u2oVlSZ94RiBps7CuhRoz-kcIRu0TMchJpUXtObkqZiNw-PoBrdFF4eShimfL4f7S7keLLMpN_-60WrFmsveBHkhBkvaFMKqs_w4_CyrHZDhFsLglzcL69aJP6DxabDTpQMYKsWztN9340rwQM2XRY3soMpeQ";

  

    @Test
    public void Get_Current_Users_Profile() {
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .when()
                .get("https://api.spotify.com/v1/me");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Get_Users_Top_Items() {
        // variables
        String type = "artists";
        String timeRange = "medium_term";
        int limit = 10;
        int offset = 0;

        // api call
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("time_range", timeRange)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when()
                .get("https://api.spotify.com/v1/me/top/" + type);
        // print response
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void get_users_profile() {
        String user_id = "31k3cbonvckxuvpyb7ocgmfkrkee";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .when()
                .get("https://api.spotify.com/v1/users/31k3cbonvckxuvpyb7ocgmfkrkee");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void FollowPlayList() {
        String playlist_id = "3cEYpjA9oz9GiPac4AsH4n";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .header("Content-Type", "application/json")
                .body("""
                                        {
                            "public": false
                        }
                                        """)
                .when()
                .put("https://api.spotify.com/v1/playlists/" + playlist_id + "/followers");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Unfollow_Playlist() {
        String playlist_id = "3cEYpjA9oz9GiPac4AsH4n";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .when()
                .delete("https://api.spotify.com/v1/playlists/" + playlist_id + "/followers");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Followed_Artists() {
        String type = "artist";
        int limit = 10;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("type", type)
                .queryParam("limit", limit)
                .when()
                .get("https://api.spotify.com/v1/me/following");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Follow_Artists_or_users() {
        String ids = "1wRPtKGflJrBx9BmLsSwlU,2CIMQHirSU0MQqyYHq0eOx,4YRxDV8wJFPHPTeXepOstw";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .header("Content-Type", "application/json")
                .queryParam("type", "artist")
                .queryParam("ids", ids)
                .body("""
                                        {
                            "ids": [
                                "string"
                            ]
                        }
                                        """)
                .when()
                .put("https://api.spotify.com/v1/me/following");
        response.prettyPrint();
        response.then().assertThat().statusCode(204);
    }

    @Test
    public void unfollow_Artists_or_users() {
        String type = "artist";
        String ids = "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .header("Content-Type", "application/json")
                .queryParam("ids", ids)
                .queryParam("type", type)
                .body("""
                                        {
                            "ids": [
                                "string"
                            ]
                        }
                                        """)
                .when()
                .delete("https://api.spotify.com/v1/me/following");
        response.prettyPrint();
        response.then().assertThat().statusCode(oneOf(200, 204));
    }

    @Test
    public void Check_if_user_follows_Artists_or_user() {
        String type = "artist";
        String ids = "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("type", type)
                .queryParam("ids", ids)
                .when()
                .get("https://api.spotify.com/v1/me/following/contains");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Check_if_current_user_follows_playlist() {
        String playlist_id = "3cEYpjA9oz9GiPac4AsH4n";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .when().get("https://api.spotify.com/v1/playlists/" + playlist_id + "/followers/contains");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    // -----------------------------------------------------------------------------------------------------------------------------------------
    // track
    // ------------------------------------------
    @Test
    public void Get_Track() {
        String id = "11dFghVXANMlKmJXsNCbNl";
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("id", id)
                .queryParam("market", market)
                .when()
                .get("https://api.spotify.com/v1/tracks/" + id);
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Get_several_Tracks() {
        String market = "ES";
        String ids = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("ids", ids)
                .when()
                .get("https://api.spotify.com/v1/tracks");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Get_User_Saved_Tracks() {
        String market = "ES";
        int limit = 10;
        int offset = 5;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when()
                .get("https://api.spotify.com/v1/me/tracks");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Save_Tracks_for_Current_User() {
        String ids = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .header("Content-Type", "application/json")
                .queryParam("ids", ids)
                .body("""
                                        {
                            "ids": [
                                "string"
                            ]
                        }
                                        """)
                .when().put("https://api.spotify.com/v1/me/tracks");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void remove_users_saved_Trackes() {
        String ids = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .header("Content-Type", "application/json")
                .body("""
                                        {
                            "ids": [
                                "string"
                            ]
                        }
                                        """)
                .queryParam("ids", ids)
                .when().delete("https://api.spotify.com/v1/me/tracks");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Check_users_saved_tracks() {
        String ids = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("ids", ids)
                .when().get("https://api.spotify.com/v1/me/tracks/contains");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_several_Tracks_Audio_features() {
        String ids = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("ids", ids)
                .when().get("https://api.spotify.com/v1/audio-features");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Tracks_Audio_Features() {
        String id = "11dFghVXANMlKmJXsNCbNl";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("id", id)
                .when().get("https://api.spotify.com/v1/audio-features/" + id);
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Tracks_Audio_Analysis() {
        String id = "11dFghVXANMlKmJXsNCbNl";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("id", id)
                .when().get("https://api.spotify.com/v1/audio-analysis/" + id);
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Recommendations() {
        int limit = 10;
        String market = "ES";
        String seed_artists = "4NHQUGzhtTLFvgF5SZesLK";
        String seed_genres = "classical,country";
        String seed_tracks = "0c6xIDDpzE81m2q797ordA";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("limit", limit)
                .queryParam("seed_tracks", seed_tracks)
                .queryParam("seed_artists", seed_artists)
                .queryParam("seed_genres", seed_genres)
                .queryParam("market", market)
                .when().get("https://api.spotify.com/v1/recommendations");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    // ---------------------------------------------------------------------------------------------------
    // Shows
    // ------------------------------------------------------------------------------------------------------------
    @Test
    public void save_Shows_for_Current_User() {
        String ids = "5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("ids", ids)
                .when().put("https://api.spotify.com/v1/me/shows");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void GetShow() {
        String market = "ES";
        String id = "38bS44xjbVVZ3No3ByF1dJ";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("id", id)
                .when().get("https://api.spotify.com/v1/shows/" + id);
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Get_severals_Shows() {
        String market = "ES";
        String ids = "5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("ids", ids)
                .when().get("https://api.spotify.com/v1/shows");
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Get_Show_Episodes() {
        String id = "38bS44xjbVVZ3No3ByF1dJ";
        String market = "ES";
        int limit = 10;
        int offset = 5;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("id", id)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when().get("https://api.spotify.com/v1/shows/" + id + "/episodes");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Get_Users_saves_shows() {
        int limit = 10;
        int offset = 5;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when().get("https://api.spotify.com/v1/me/shows");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Save_shows_for_current_user() {
        String ids = "5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("ids", ids)
                .when().put("https://api.spotify.com/v1/me/shows");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Remove_users_saved_shows() {
        String ids = "5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ";
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("ids", ids)
                .when().delete("https://api.spotify.com/v1/me/shows");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void check_users_saved_shows() {
        String ids = "5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("ids", ids)
                .when().get("https://api.spotify.com/v1/me/shows/contains");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    // ------------------------------------------
    // Search
    // ------------------------------------------
    @Test
    public void Search_for_item() {
        String q = "remaster%20track:Doxy%20artist:Miles%20Davis";
        String type = "album";
        String market = "ES";
        int limit = 10;
        int offset = 5;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("q", q)
                .queryParam("type", type)
                .queryParam("market", market)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when().get("https://api.spotify.com/v1/search");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    // -----------------------------------------------------------------------------------------------
    // Playlist

    @Test
    public void create_playlist() {
        String user_id = "31k3cbonvckxuvpyb7ocgmfkrkee";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .body("""
                                    {
                            "name": "pankaj ki playlist",
                            "description": "pankaj ke panjabi songs ki new playlist",
                            "public": false
                        }
                                    """)
                .queryParam("user_id", user_id)
                .when().post("https://api.spotify.com/v1/users/" + user_id + "/playlists");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);

    }

    @Test
    public void Add_items_to_playlist() {
        String playlist_id = "3cEYpjA9oz9GiPac4AsH4n";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .header("Content-Type", "application/json")
                .body("""
                                    {
                            "uris": [
                                "string"
                            ],
                            "position": 0
                        }

                                        """)
                .when().post("https://api.spotify.com/v1/playlists/" + playlist_id + "/tracks");
        response.prettyPrint();
        response.then().assertThat().statusCode(403);

    }

    @Test
    public void get_PlayList() {
        String playlist_id = "5s1vyqzMG25hMSxj9Efh00";
        String markte = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("markte", markte)
                .queryParam("playlist_id", playlist_id)
                .when().get("https://api.spotify.com/v1/playlists/" + playlist_id);
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Change_PlayList_Details() {
        String playlist_id = "5s1vyqzMG25hMSxj9Efh00";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .body("""
                                    {
                            "name": "ye hai ab vishal ki playlist",
                            "description": "Updated playlist description is vishal ki playlist for bbhojpuri songs ",
                            "public": false
                        }
                                    """)
                .queryParam("playlist_id", playlist_id)
                .when().put("https://api.spotify.com/v1/playlists/" + playlist_id);
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void GEt_Playlist_Items() {
        String playlist_id = "5s1vyqzMG25hMSxj9Efh00";
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("playlist_id", playlist_id)
                .queryParam("markte", market)
                .when().get("https://api.spotify.com/v1/playlists/" + playlist_id + "/tracks");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Update_playlist_items() {
        String playlist_id = "5s1vyqzMG25hMSxj9Efh00";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .header("Content-Type", "application/json")
                .body("""
                        {
                            "range_start": 1,
                            "insert_before": 10,
                            "range_length": 5
                        }
                        """)
                // .queryParam("playlist_id", playlist_id)
                .when().put("https://api.spotify.com/v1/playlists/" + playlist_id + "/tracks");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Remove_Playlist_items() {
        String playlist_id = "3cEYpjA9oz9GiPac4AsH4n";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .header("Content-Type", "application/json")
                .body("""
                                                       {
                            "tracks": [
                                {
                                    "uri": "spotify:track:61R3DMR4U2n25qgX9ryciJ?si=a47a3be92f144412"
                                },
                                {
                                    "uri": "spotify:track:5VKvDHouRJ4a8VNXrzTWnc?si=8fccfa5f6f404a42"
                                }
                                // Add more tracks as needed
                            ],
                            "snapshot_id": "Zk3QMAAAAABAyhO8lufTaQ8pAHkg+O7e"
                        }

                                                        """)
                .when().delete("https://api.spotify.com/v1/playlists/" + playlist_id + "/tracks");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Get_curent_users_playlists() {
        int limit = 10;
        int offset = 5;

        Response response = given()

                .header("Authorization", "Bearer " + access_Token)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when().get("https://api.spotify.com/v1/me/playlists");

        response.prettyPrint();

        response.then().assertThat().statusCode(200);

    }

    @Test
    public void GEt_users_playlists() {
        String user_id = "31k3cbonvckxuvpyb7ocgmfkrkee";
        int limit = 10;
        int offset = 5;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when().get("https://api.spotify.com/v1/users/" + user_id + "/playlists");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Get_featured_playlist() {
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .when().get("https://api.spotify.com/v1/browse/featured-playlists");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Categorys_playlists() {
        String category_id = "dinner";
        int limit = 10;
        int offset = 5;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("category_id", category_id)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when().get("https://api.spotify.com/v1/browse/categories/" + category_id + "/playlists");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Get_Playlist_cover_image() {
        String playlist_id = "3cEYpjA9oz9GiPac4AsH4n";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("playlist_id", playlist_id)
                .when().get("https://api.spotify.com/v1/playlists/" + playlist_id + "/images");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    // --------------------------------------------------------------------------------
    // player
    // -------------------------------------------------------------------------------------

    @Test
    public void get_Playback_State() {
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .when().get("https://api.spotify.com/v1/me/player");
        response.prettyPrint();
        response.then().assertThat().statusCode(oneOf(200, 204));

    }

    @Test
    public void Transfer_Playback() {
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .header("Content-Type", "application/json")
                .body("""
                                {
                            "device_ids": [
                                "74ASZWbe4lXaubB36ztrGX"
                            ]
                        }
                                """)
                .when().put("https://api.spotify.com/v1/me/player");
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Availabe_Devices() {
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .when().get("https://api.spotify.com/v1/me/player/devices");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Currently_Playing_Track() {
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .when().get("https://api.spotify.com/v1/me/player/currently-playing");
        response.prettyPrint();
        response.then().assertThat().statusCode(oneOf(200,204));
    }

    public void Start_Resume_PlayBack() {
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .header("Content-Type", "application/json")
                .body("""
                                {
                            "context_uri": "spotify:album:5ht7ItJgpBH7W6vJ5BqpPr",
                            "offset": {
                                "position": 5
                            },
                            "position_ms": 0
                        }
                                """)
                .when().put("https://api.spotify.com/v1/me/player/play");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Pause_PlayBack() {
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .when().put("https://api.spotify.com/v1/me/player/pause");
        response.prettyPrint();
        response.then().assertThat().statusCode(204);
    }

    @Test
    public void Skip_to_Next() {
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .when().post("https://api.spotify.com/v1/me/player/next");
        response.prettyPrint();
        response.then().assertThat().statusCode(204);
    }

    @Test
    public void Skip_to_previous() {
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .when().post("https://api.spotify.com/v1/me/player/previous");
        response.prettyPrint();
        response.then().assertThat().statusCode(204);
    }

    @Test
    public void Seek_to_Position() {
        long position_ms = 25000;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("position_ms", position_ms)
                .when().put("https://api.spotify.com/v1/me/player/seek");
        response.prettyPrint();
        response.then().assertThat().statusCode(204);
    }

    @Test
    public void Set_Repeat_Mode() {
        String state = "context";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("state", state)
                .when().put();
        response.prettyPrint();
        response.then().assertThat().statusCode(204);

    }

    @Test
    public void Set_Playback_Volume() {
        int volume_percent = 50;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("volume_percent", volume_percent)
                .when().put();
        response.prettyPrint();
        response.then().assertThat().statusCode(204);
    }

    @Test
    public void Toggle_Playback_Shuffle() {
        String state = "true";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("state", state)
                .when().put();
        response.prettyPrint();
        response.then().assertThat().statusCode(204);

    }

    @Test
    public void GetRecently_played_Tracks() {
        int limit = 10;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("limit", limit)
                .when().get("https://api.spotify.com/v1/me/player/recently-played");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_the_users_Queue() {
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .when().get("https://api.spotify.com/v1/me/player/queue");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Available_Markets() {
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .when().get("https://api.spotify.com/v1/markets");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void GEt_Availabe_Genre_Seeds() {
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .when().get("https://api.spotify.com/v1/recommendations/available-genre-seeds");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Save_episodes_for_current_user() {
        String ids = "77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .body("""
                                        {
                            "ids": [
                                "string"
                            ]
                        }
                                        """)
                .queryParam("ids", ids)
                .when().put("https://api.spotify.com/v1/me/episodes");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Episode() {
        String id = "512ojhOuo1ktJprKbVcKyQ";
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("id", id)
                .when().get("https://api.spotify.com/v1/episodes/" + id);
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Check_users_saved_Episodes() {
        String ids = "77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("ids", ids)
                .when().get("https://api.spotify.com/v1/me/episodes/contains");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Remove_users_saved_Episodes() {
        String ids = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("ids", ids)
                .when().delete("https://api.spotify.com/v1/me/episodes");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_several_Episodes() {
        String ids = "77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf";
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("ids", ids)
                .when().get("https://api.spotify.com/v1/episodes");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void get_Users_saved_Episodes() {
        String market = "ES";
        int limit = 10;
        int offset = 5;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when().get("https://api.spotify.com/v1/me/episodes");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_a_Chapter() {
        String id = "0D5wENdkdwbqlrHoaJ9g29";
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("id", id)
                .when().get("https://api.spotify.com/v1/chapters/" + id);
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_saveral_Chapters() {
        String ids = "0IsXVP0JmcB2adSE338GkK,3ZXb8FKZGU0EHALYX6uCzU,0D5wENdkdwbqlrHoaJ9g29";
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("ids", ids)
                .when().get("https://api.spotify.com/v1/chapters");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Several_Browse_Categories() {
        int limit = 10;
        int offset = 5;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when().get("https://api.spotify.com/v1/browse/categories");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Get_single_Browse_Category() {
        String category_id = "dinner";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("category_id", category_id)
                .when().get("https://api.spotify.com/v1/browse/categories/" + category_id);
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    // -----------------------------------------------------------------------------------------------
    // Albums
    @Test
    public void GetAlbum() {
        String id = "4aawyAB9vmqN3uQ7FjRGTy";
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("id", id)
                .when().get("https://api.spotify.com/v1/albums/" + id);
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void get_Several_Albums() {
        String ids = "382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc";
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("ids", ids)
                .when().get("https://api.spotify.com/v1/albums");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Album_Tracks() {
        String id = "4aawyAB9vmqN3uQ7FjRGTy";
        String market = "ES";
        int limit = 10;
        int offset = 5;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("id", id)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when().get("https://api.spotify.com/v1/albums/" + id + "/tracks");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Save_albums_for_current_user() {
        String ids = "382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("ids", ids)
                .body("""
                                {
                            "ids": [
                                "string"
                            ]
                        }
                                """)
                .when().put("https://api.spotify.com/v1/me/albums");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_users_saved_Albums() {
        int limit = 10;
        int offset = 5;
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("offset", offset)
                .queryParam("limit", limit)
                .when().get("https://api.spotify.com/v1/me/albums");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Check_users_saved_Albums() {
        String ids = "382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("ids", ids)
                .when().get("https://api.spotify.com/v1/me/albums/contains");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_New_Releases() {
        int limit = 10;
        int offset = 5;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when().get("https://api.spotify.com/v1/browse/new-releases");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void remove_Users_saved_Albums() {
        String ids = "382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .header("Content-Type", "application/json")
                .body("""
                                {
                            "ids": [
                                "string"
                            ]
                        }
                                """)
                .queryParam("ids", ids)
                .when().delete("https://api.spotify.com/v1/me/albums");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    // --------------------------------------------------------------------------------------------------
    // Artist
    @Test
    public void Get_Artist() {
        String id = "0TnOYISbd1XYRBk9myaseg";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("id", id)
                .when().get("https://api.spotify.com/v1/artists/" + id);
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Several_Artists() {
        String ids = "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("ids", ids)
                .when().get("https://api.spotify.com/v1/artists");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Artists_Albums() {
        String id = "0TnOYISbd1XYRBk9myaseg";
        String market = "ES";
        int limit = 10;
        int offset = 5;
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("id", id)
                .queryParam("market", market)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when().get("https://api.spotify.com/v1/artists/" + id + "/albums");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Artists_top_Tracks() {
        String id = "0TnOYISbd1XYRBk9myaseg";
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("id", id)
                .when().get("https://api.spotify.com/v1/artists/" + id + "/top-tracks");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Get_Artists_Related_Artists() {
        String id = "0TnOYISbd1XYRBk9myaseg";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("id", id)
                .when().get("https://api.spotify.com/v1/artists/" + id + "/related-artists");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    // ----------------------------------------------------------------------------------------------------------------------------------------
    // Audiobook
    @Test
    public void GEt_an_Audiobook() {
        String id = "7iHfbu1YPACw6oZPAFJtqe";
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("id", id)
                .when().get("https://api.spotify.com/v1/audiobooks/" + id);
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_Several_AudioBooks() {
        String ids = "18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe";
        String market = "ES";
        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("market", market)
                .queryParam("ids", ids)
                .when().get("https://api.spotify.com/v1/audiobooks");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void Get_AudioBook_Chapters() {
        String id = "7iHfbu1YPACw6oZPAFJtqe";
        String market = "ES";
        int limit = 10;
        int offset = 5;

        Response response = given()
                .header("Authorization", "Bearer " + access_Token)
                .queryParam("id", id)
                .queryParam("market", market)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when().get("https://api.spotify.com/v1/audiobooks/" + id + "/chapters");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void Get_users_saved_Audiobooks()
    {
    int limit=10;
    int offset=5;
    Response response=given()
    .header("Authorization" ,"Bearer " +access_Token)
.queryParam("limit", limit)
.queryParam("offset", offset)
.when().get("https://api.spotify.com/v1/me/audiobooks");
response.prettyPrint();
response.then().assertThat().statusCode(200);
    }

    @Test
    public void Save_Audiobooks_For_Current_User()
    {
       String id="18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe";
       Response response=given()
       .header("Authorization" ,"Bearer " +access_Token)
.queryParam("id", id)
.when().put("https://api.spotify.com/v1/me/audiobooks");
response.prettyPrint();
response.then().assertThat().statusCode(200); 
    }

    @Test
    public void Remove_users_saved_audioBooks()
    {
        String ids="18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe";
        Response response=given()
        .header("Authorization" ,"Bearer " +access_Token)
.queryParam("ids", ids)
.when().delete("https://api.spotify.com/v1/me/audiobooks");

response.prettyPrint();
response.then().assertThat().statusCode(200);
    }

    @Test
    public void Check_Users_saved_AudioBooks()
    {
        String ids="18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe";
        Response response=given()
        .header("Authorization" ,"Bearer " +access_Token)
        .queryParam("ids", ids)
        .when().get("https://api.spotify.com/v1/me/audiobooks/contains");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }


}
