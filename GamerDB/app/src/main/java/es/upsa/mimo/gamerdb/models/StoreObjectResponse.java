/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 10/8/2020
 */

package es.upsa.mimo.gamerdb.models;

import com.google.gson.annotations.SerializedName;

public class StoreObjectResponse {

    private int id;
    private String name;
    private String slug;
    private String domain;
    @SerializedName("games_count")
    private int gamesCount;
    @SerializedName("image_background")
    private String imageBackground;

    public StoreObjectResponse(int id,
                               String name,
                               String slug,
                               String domain,
                               int gamesCount,
                               String imageBackground) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.domain = domain;
        this.gamesCount = gamesCount;
        this.imageBackground = imageBackground;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getGamesCount() {
        return gamesCount;
    }

    public void setGamesCount(int gamesCount) {
        this.gamesCount = gamesCount;
    }

    public String getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(String imageBackground) {
        this.imageBackground = imageBackground;
    }
}
