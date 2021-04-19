/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 14/9/2020
 */

package es.upsa.mimo.gamerdb.network.apiservice;

import java.util.Map;

import es.upsa.mimo.gamerdb.models.GenreListResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GenreAPIService {

    @GET("genres")
    Single<GenreListResponse> getGenres(@QueryMap Map<String, String> queryParams);
}
