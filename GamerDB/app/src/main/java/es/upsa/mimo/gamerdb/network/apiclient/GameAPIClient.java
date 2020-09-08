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
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class GameAPIClient {

    private GameAPIService api = APIClient.getRetrofit().create(GameAPIService.class);
    private Scheduler subscriberScheduler = Schedulers.io();
    private Scheduler observerScheduler = AndroidSchedulers.mainThread();

    public Single<GameListResponse> getGamesObserver(int page, int pageSize, String query) {

        Map<String, String> params = new HashMap<>();
        params.put(Constants.PAGE_PARAM, String.valueOf(page));
        params.put(Constants.PAGE_SIZE_PARAM, String.valueOf(pageSize));
        if (query != null) {
            params.put(Constants.SEARCH_PARAM, query);
        }

        return api.getGames(params).subscribeOn(subscriberScheduler).observeOn(observerScheduler);
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