/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 6/8/2020
 */

package es.upsa.mimo.gamerdb.network.apiclient;

import es.upsa.mimo.gamerdb.models.ErrorResponse;

public interface CompletionHandler<T> {
    void success(T t);
    void failure(ErrorResponse error);
}
