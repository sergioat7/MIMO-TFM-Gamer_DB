/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 14/9/2020
 */

package es.upsa.mimo.gamerdb.network.apiclient;

import java.util.HashMap;
import java.util.Map;

import es.upsa.mimo.gamerdb.models.GenreListResponse;
import es.upsa.mimo.gamerdb.network.apiservice.GenreAPIService;
import es.upsa.mimo.gamerdb.utils.Constants;
import io.reactivex.Single;

public class GenreAPIClient {

    private final GenreAPIService api = APIClient.getRetrofit().create(GenreAPIService.class);

    public Single<GenreListResponse> getGenresObserver(int page, int pageSize) {

        Map<String, String> params = new HashMap<>();
        params.put(Constants.PAGE_PARAM, String.valueOf(page));
        params.put(Constants.PAGE_SIZE_PARAM, String.valueOf(pageSize));
        return api.getGenres(params).subscribeOn(Constants.SUBSCRIBER_SCHEDULER).observeOn(Constants.OBSERVER_SCHEDULER);
    }
}
