package es.upsa.mimo.gamerdb.network.apiclient;

public interface CompletionHandler<T, U> {
    void success(T t);
    void failure(U u);
}
