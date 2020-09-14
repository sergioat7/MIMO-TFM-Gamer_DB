/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 13/9/2020
 */

package es.upsa.mimo.gamerdb.network.apiclient;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import es.upsa.mimo.gamerdb.models.PlatformListResponse;
import es.upsa.mimo.gamerdb.network.apiservice.PlatformAPIService;
import es.upsa.mimo.gamerdb.utils.Constants;
import io.reactivex.Single;

public class PlatformAPIClient {

    private PlatformAPIService api = APIClient.getRetrofit().create(PlatformAPIService.class);

    public Single<PlatformListResponse> getPlatformsObserver(int page, int pageSize) {

        Map<String, String> params = new HashMap<>();
        params.put(Constants.PAGE_PARAM, String.valueOf(page));
        params.put(Constants.PAGE_SIZE_PARAM, String.valueOf(pageSize));
        return api.getPlatforms(params).subscribeOn(Constants.SUBSCRIBER_SCHEDULER).observeOn(Constants.OBSERVER_SCHEDULER);
    }
}
