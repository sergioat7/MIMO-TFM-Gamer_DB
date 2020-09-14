/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 10/8/2020
 */

package es.upsa.mimo.gamerdb.network.apiclient;

import java.util.HashMap;
import java.util.Map;
import es.upsa.mimo.gamerdb.models.GameListResponse;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.models.ScreenshotListResponse;
import es.upsa.mimo.gamerdb.network.apiservice.GameAPIService;
import es.upsa.mimo.gamerdb.utils.Constants;
import io.reactivex.Single;

public class GameAPIClient {

    private GameAPIService api = APIClient.getRetrofit().create(GameAPIService.class);

    public Single<GameListResponse> getGamesObserver(int page,
                                                     int pageSize,
                                                     String query,
                                                     String platforms) {

        Map<String, String> params = new HashMap<>();
        params.put(Constants.PAGE_PARAM, String.valueOf(page));
        params.put(Constants.PAGE_SIZE_PARAM, String.valueOf(pageSize));
        if (query != null) {
            params.put(Constants.SEARCH_PARAM, query);
        }
        if (platforms != null) {
            params.put(Constants.PLATFORMS_PARAM, platforms);
        }
        return api.getGames(params).subscribeOn(Constants.SUBSCRIBER_SCHEDULER).observeOn(Constants.OBSERVER_SCHEDULER);
    }

    public Single<GameResponse> getGame(int gameId) {
        return api.getGame(gameId).subscribeOn(Constants.SUBSCRIBER_SCHEDULER).observeOn(Constants.OBSERVER_SCHEDULER);
    }

    public Single<ScreenshotListResponse> getScreenshots(int gameId) {
        return api.getScreenshots(gameId).subscribeOn(Constants.SUBSCRIBER_SCHEDULER).observeOn(Constants.OBSERVER_SCHEDULER);
    }
}