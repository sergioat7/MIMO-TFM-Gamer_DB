package es.upsa.mimo.gamerdb.network.apiclient;

import es.upsa.mimo.gamerdb.models.ErrorResponse;

public interface CompletionHandler<T> {
    void success(T t);
    void failure(ErrorResponse error);
}
