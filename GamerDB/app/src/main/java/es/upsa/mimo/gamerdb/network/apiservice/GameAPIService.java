/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 10/8/2020
 */

package es.upsa.mimo.gamerdb.network.apiservice;

import java.util.Map;
import es.upsa.mimo.gamerdb.models.AchievementListResponse;
import es.upsa.mimo.gamerdb.models.DeveloperListResponse;
import es.upsa.mimo.gamerdb.models.GameListResponse;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.models.ScreenshotListResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface GameAPIService {

    @Headers("User-Agent:GamerDB")
    @GET("games")
    Single<GameListResponse> getGames(@QueryMap Map<String, String> queryParams);

    @Headers("User-Agent:GamerDB")
    @GET("games/{gameId}")
    Single<GameResponse> getGame(@Path(value = "gameId") int gameId);

    @Headers("User-Agent:GamerDB")
    @GET("games/{gameId}/screenshots")
    Single<ScreenshotListResponse> getScreenshots(@Path(value = "gameId") int gameId);

    @Headers("User-Agent:GamerDB")
    @GET("games/{gameId}/game-series")
    Single<GameListResponse> getGameSeries(@Path(value = "gameId") int gameId, @QueryMap Map<String, String> queryParams);

    @Headers("User-Agent:GamerDB")
    @GET("games/{gameId}/suggested")
    Single<GameListResponse> getGamesSuggested(@Path(value = "gameId") int gameId, @QueryMap Map<String, String> queryParams);

    @Headers("User-Agent:GamerDB")
    @GET("games/{gameId}/additions")
    Single<GameListResponse> getGameAdditions(@Path(value = "gameId") int gameId, @QueryMap Map<String, String> queryParams);

    @Headers("User-Agent:GamerDB")
    @GET("games/{gameId}/development-team")
    Single<DeveloperListResponse> getDevelopers(@Path(value = "gameId") int gameId, @QueryMap Map<String, String> queryParams);

    @Headers("User-Agent:GamerDB")
    @GET("games/{gameId}/achievements")
    Single<AchievementListResponse> getAchievements(@Path(value = "gameId") int gameId, @QueryMap Map<String, String> queryParams);
}