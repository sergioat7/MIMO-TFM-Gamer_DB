package es.upsa.mimo.gamerdb.models;

public class StoreResponse {

    private int id;
    private String url;
    private StoreObjectResponse store;

    public StoreResponse(int id,
                         String url,
                         StoreObjectResponse store) {
        this.id = id;
        this.url = url;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public StoreObjectResponse getStore() {
        return store;
    }

    public void setStore(StoreObjectResponse store) {
        this.store = store;
    }
}
