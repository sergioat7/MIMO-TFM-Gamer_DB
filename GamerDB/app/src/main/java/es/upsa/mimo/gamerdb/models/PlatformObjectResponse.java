/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 10/8/2020
 */

package es.upsa.mimo.gamerdb.models;

import com.google.gson.annotations.SerializedName;

public class PlatformObjectResponse {

    private int id;
    private String name;
    private String slug;
    private String image;
    @SerializedName("year_end")
    private String yearEnd;
    @SerializedName("year_start")
    private String yearStart;
    @SerializedName("games_count")
    private int gamesCount;
    @SerializedName("image_background")
    private String imageBackground;

    public PlatformObjectResponse(int id,
                                  String name,
                                  String slug,
                                  String image,
                                  String yearEnd,
                                  String yearStart,
                                  int gamesCount,
                                  String imageBackground) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.image = image;
        this.yearEnd = yearEnd;
        this.yearStart = yearStart;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(String yearEnd) {
        this.yearEnd = yearEnd;
    }

    public String getYearStart() {
        return yearStart;
    }

    public void setYearStart(String yearStart) {
        this.yearStart = yearStart;
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
