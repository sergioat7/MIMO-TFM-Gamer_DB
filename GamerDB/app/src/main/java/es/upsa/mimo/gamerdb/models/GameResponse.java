package es.upsa.mimo.gamerdb.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameResponse {

    private int id;
    private String name;
    @SerializedName("description_raw")
    private String description;
    private String released;
    @SerializedName("background_image")
    private String backgroundImage;
    @SerializedName("background_image_additional")
    private String backgroundImageAdditional;
    private String website;
    private double rating;
    @SerializedName("rating_top")
    private double ratingTop;
    private List<RatingResponse> ratings;
    @SerializedName("screenshots_count")
    private int screenshotsCount;
    private List<PlatformResponse> platforms;
    private List<StoreResponse> stores;
    private List<DeveloperResponse> developers;
    private List<GenreResponse> genres;
    private List<TagResponse> tags;
    private List<PublisherResponse> publishers;
    @SerializedName("esrb_rating")
    private EsrbResponse esrbRating;
    @SerializedName("short_screenshots")
    private List<ScreenshotResponse> shortScreenshots;

    public GameResponse(int id,
                        String name,
                        String description,
                        String released,
                        String backgroundImage,
                        String backgroundImageAdditional,
                        String website,
                        double rating,
                        double ratingTop,
                        List<RatingResponse> ratings,
                        int screenshotsCount,
                        List<PlatformResponse> platforms,
                        List<StoreResponse> stores,
                        List<DeveloperResponse> developers,
                        List<GenreResponse> genres,
                        List<TagResponse> tags,
                        List<PublisherResponse> publishers,
                        EsrbResponse esrbRating,
                        List<ScreenshotResponse> shortScreenshots) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.released = released;
        this.backgroundImage = backgroundImage;
        this.backgroundImageAdditional = backgroundImageAdditional;
        this.website = website;
        this.rating = rating;
        this.ratingTop = ratingTop;
        this.ratings = ratings;
        this.screenshotsCount = screenshotsCount;
        this.platforms = platforms;
        this.stores = stores;
        this.developers = developers;
        this.genres = genres;
        this.tags = tags;
        this.publishers = publishers;
        this.esrbRating = esrbRating;
        this.shortScreenshots = shortScreenshots;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getBackgroundImageAdditional() {
        return backgroundImageAdditional;
    }

    public void setBackgroundImageAdditional(String backgroundImageAdditional) {
        this.backgroundImageAdditional = backgroundImageAdditional;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRatingTop() {
        return ratingTop;
    }

    public void setRatingTop(double ratingTop) {
        this.ratingTop = ratingTop;
    }

    public List<RatingResponse> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingResponse> ratings) {
        this.ratings = ratings;
    }

    public int getScreenshotsCount() {
        return screenshotsCount;
    }

    public void setScreenshotsCount(int screenshotsCount) {
        this.screenshotsCount = screenshotsCount;
    }

    public List<PlatformResponse> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<PlatformResponse> platforms) {
        this.platforms = platforms;
    }

    public List<StoreResponse> getStores() {
        return stores;
    }

    public void setStores(List<StoreResponse> stores) {
        this.stores = stores;
    }

    public List<DeveloperResponse> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<DeveloperResponse> developers) {
        this.developers = developers;
    }

    public List<GenreResponse> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreResponse> genres) {
        this.genres = genres;
    }

    public List<TagResponse> getTags() {
        return tags;
    }

    public void setTags(List<TagResponse> tags) {
        this.tags = tags;
    }

    public List<PublisherResponse> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<PublisherResponse> publishers) {
        this.publishers = publishers;
    }

    public EsrbResponse getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(EsrbResponse esrbRating) {
        this.esrbRating = esrbRating;
    }

    public List<ScreenshotResponse> getShortScreenshots() {
        return shortScreenshots;
    }

    public void setShortScreenshots(List<ScreenshotResponse> shortScreenshots) {
        this.shortScreenshots = shortScreenshots;
    }
}