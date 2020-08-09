package es.upsa.mimo.gamerdb.models;

import java.util.List;

public class GameListResponse {

    private int count;
    private String next;
    private String previous;
    private List<GameResponse> results;

    public GameListResponse(int count,
                            String next,
                            String previous,
                            List<GameResponse> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<GameResponse> getResults() {
        return results;
    }

    public void setResults(List<GameResponse> results) {
        this.results = results;
    }
}
