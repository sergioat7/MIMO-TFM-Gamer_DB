/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 13/9/2020
 */

package es.upsa.mimo.gamerdb.models;

import java.util.List;

public class PlatformListResponse {

    private int count;
    private String next;
    private String previous;
    private List<PlatformObjectResponse> results;

    public PlatformListResponse(int count,
                                String next,
                                String previous,
                                List<PlatformObjectResponse> results) {
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

    public List<PlatformObjectResponse> getResults() {
        return results;
    }

    public void setResults(List<PlatformObjectResponse> results) {
        this.results = results;
    }
}
