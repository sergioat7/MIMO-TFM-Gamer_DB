/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 10/8/2020
 */

package es.upsa.mimo.gamerdb.network.apiclient;

import java.util.HashMap;
import java.util.Map;

import es.upsa.mimo.gamerdb.models.AchievementListResponse;
import es.upsa.mimo.gamerdb.models.DeveloperListResponse;
import es.upsa.mimo.gamerdb.models.GameListResponse;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.models.ScreenshotListResponse;
import es.upsa.mimo.gamerdb.network.apiservice.GameAPIService;
import es.upsa.mimo.gamerdb.utils.Constants;
import io.reactivex.Single;

public class GameAPIClient {

    private final GameAPIService api = APIClient.getRetrofit().create(GameAPIService.class);

    public Single<GameListResponse> getGamesObserver(int page,
                                                     int pageSize,
                                                     String query,
                                                     String ordering,
                                                     String platforms,
                                                     String genres) {

        Map<String, String> params = new HashMap<>();
        params.put(Constants.PAGE_PARAM, String.valueOf(page));
        params.put(Constants.PAGE_SIZE_PARAM, String.valueOf(pageSize));
        if (query != null) {
            params.put(Constants.SEARCH_PARAM, query);
        }
        if (ordering != null) {
            params.put(Constants.ORDERING_PARAM, ordering);
        }
        if (platforms != null) {
            params.put(Constants.PLATFORMS_PARAM, platforms);
        }
        if (genres != null) {
            params.put(Constants.GENRES_PARAM, genres);
        }
        return api.getGames(params).subscribeOn(Constants.SUBSCRIBER_SCHEDULER).observeOn(Constants.OBSERVER_SCHEDULER);
    }

    public Single<GameResponse> getGame(int gameId) {
        return api.getGame(gameId).subscribeOn(Constants.SUBSCRIBER_SCHEDULER).observeOn(Constants.OBSERVER_SCHEDULER);
    }

    public Single<ScreenshotListResponse> getScreenshots(int gameId) {
        return api.getScreenshots(gameId).subscribeOn(Constants.SUBSCRIBER_SCHEDULER).observeOn(Constants.OBSERVER_SCHEDULER);
    }

    public Single<GameListResponse> getGameSeries(int gameId, int page, int pageSize) {

        Map<String, String> params = new HashMap<>();
        params.put(Constants.PAGE_PARAM, String.valueOf(page));
        params.put(Constants.PAGE_SIZE_PARAM, String.valueOf(pageSize));
        return api.getGameSeries(gameId, params).subscribeOn(Constants.SUBSCRIBER_SCHEDULER).observeOn(Constants.OBSERVER_SCHEDULER);
    }

    public Single<GameListResponse> getGamesSuggested(int gameId, int page, int pageSize) {

        Map<String, String> params = new HashMap<>();
        params.put(Constants.PAGE_PARAM, String.valueOf(page));
        params.put(Constants.PAGE_SIZE_PARAM, String.valueOf(pageSize));
        return api.getGamesSuggested(gameId, params).subscribeOn(Constants.SUBSCRIBER_SCHEDULER).observeOn(Constants.OBSERVER_SCHEDULER);
    }

    public Single<GameListResponse> getGameAdditions(int gameId, int page, int pageSize) {

        Map<String, String> params = new HashMap<>();
        params.put(Constants.PAGE_PARAM, String.valueOf(page));
        params.put(Constants.PAGE_SIZE_PARAM, String.valueOf(pageSize));
        return api.getGameAdditions(gameId, params).subscribeOn(Constants.SUBSCRIBER_SCHEDULER).observeOn(Constants.OBSERVER_SCHEDULER);
    }

    public Single<DeveloperListResponse> getDevelopers(int gameId, int page, int pageSize) {

        Map<String, String> params = new HashMap<>();
        params.put(Constants.PAGE_PARAM, String.valueOf(page));
        params.put(Constants.PAGE_SIZE_PARAM, String.valueOf(pageSize));
        return api.getDevelopers(gameId, params).subscribeOn(Constants.SUBSCRIBER_SCHEDULER).observeOn(Constants.OBSERVER_SCHEDULER);
    }

    public Single<AchievementListResponse> getAchievements(int gameId, int page, int pageSize) {

        Map<String, String> params = new HashMap<>();
        params.put(Constants.PAGE_PARAM, String.valueOf(page));
        params.put(Constants.PAGE_SIZE_PARAM, String.valueOf(pageSize));
        return api.getAchievements(gameId, params).subscribeOn(Constants.SUBSCRIBER_SCHEDULER).observeOn(Constants.OBSERVER_SCHEDULER);
    }
}