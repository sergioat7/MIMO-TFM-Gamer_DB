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
import retrofit2.Call;

public class GameAPIClient {

    private GameAPIService api = APIClient.getRetrofit().create(GameAPIService.class);

    public void getGames(int page, int pageSize, String query, CompletionHandler<GameListResponse> completion) {

        Map<String, String> params = new HashMap<>();
        params.put(Constants.PAGE_PARAM, String.valueOf(page));
        params.put(Constants.PAGE_SIZE_PARAM, String.valueOf(pageSize));
        if (query != null) {
            params.put(Constants.SEARCH_PARAM, query);
        }

        Call<GameListResponse> request = api.getGames(params);
        APIClient.sendServer(request,completion);
    }

    public void getGame(int gameId, CompletionHandler<GameResponse> completion) {

        Call<GameResponse> request = api.getGame(gameId);
        APIClient.sendServer(request, completion);
    }

    public void getScreenshots(int gameId, CompletionHandler<ScreenshotListResponse> completion) {

        Call<ScreenshotListResponse> request = api.getScreenshots(gameId);
        APIClient.sendServer(request, completion);
    }
}