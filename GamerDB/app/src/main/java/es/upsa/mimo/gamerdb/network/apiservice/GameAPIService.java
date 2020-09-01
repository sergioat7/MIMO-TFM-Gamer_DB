/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 10/8/2020
 */

package es.upsa.mimo.gamerdb.network.apiservice;

import java.util.Map;
import es.upsa.mimo.gamerdb.models.GameListResponse;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.models.ScreenshotListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface GameAPIService {

    @Headers("User-Agent:GamerDB")
    @GET("games")
    Call<GameListResponse> getGames(@QueryMap Map<String, Integer> queryParams);

    @Headers("User-Agent:GamerDB")
    @GET("games/{gameId}")
    Call<GameResponse> getGame(@Path(value = "gameId") int gameId);

    @Headers("User-Agent:GamerDB")
    @GET("games/{gameId}/screenshots")
    Call<ScreenshotListResponse> getScreenshots(@Path(value = "gameId") int gameId);
}