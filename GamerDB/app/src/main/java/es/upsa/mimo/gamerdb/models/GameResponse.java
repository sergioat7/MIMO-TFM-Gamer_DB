package es.upsa.mimo.gamerdb.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameResponse {

    private int id;
    private String slug;
    private String name;
    @SerializedName("name_original")
    private String nameOriginal;
    @SerializedName("description_raw")
    private String description;
    private int metacritic;
    private String released;
    private boolean tba;
    private String updated;
    @SerializedName("background_image")
    private String backgroundImage;
    @SerializedName("background_image_additional")
    private String backgroundImageAdditional;
    private String website;
    private double rating;
    @SerializedName("rating_top")
    private double ratingTop;
    private List<RatingResponse> ratings;
    private int added;
    private int playtime;
    @SerializedName("screenshots_count")
    private int screenshotsCount;
    @SerializedName("movies_count")
    private int moviesCount;
    @SerializedName("creators_count")
    private int creatorsCount;
    @SerializedName("achievements_count")
    private int achievementsCount;
    @SerializedName("parent_achievements_count")
    private int parentAchievementsCount;
    @SerializedName("redditUrl")
    private String redditUrl;
    @SerializedName("reddit_name")
    private String redditName;
    @SerializedName("reddit_description")
    private String redditDescription;
    @SerializedName("reddit_logo")
    private String redditLogo;
    @SerializedName("reddit_count")
    private int redditCount;
    @SerializedName("twitch_count")
    private int twitchCount;
    @SerializedName("youtube_count")
    private int youtubeCount;
    @SerializedName("reviews_text_count")
    private int reviewsTextCount;
    @SerializedName("ratings_count")
    private int ratingsCount;
    @SerializedName("suggestions_count")
    private int suggestionsCount;
    @SerializedName("alternative_names")
    private List<String> alternativeNames;
    @SerializedName("metacritic_url")
    private String metacriticUrl;
    @SerializedName("parents_count")
    private int parentsCount;
    @SerializedName("additions_count")
    private int additionsCount;
    @SerializedName("game_series_count")
    private int gameSeriesCount;
    @SerializedName("reviews_count")
    private int reviewsCount;
    @SerializedName("saturated_color")
    private String saturatedColor;
    @SerializedName("dominant_color")
    private String dominantColor;
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
                        String slug,
                        String name,
                        String nameOriginal,
                        String description,
                        int metacritic,
                        String released,
                        boolean tba,
                        String updated,
                        String backgroundImage,
                        String backgroundImageAdditional,
                        String website,
                        double rating,
                        double ratingTop,
                        List<RatingResponse> ratings,
                        int added,
                        int playtime,
                        int screenshotsCount,
                        int moviesCount,
                        int creatorsCount,
                        int achievementsCount,
                        int parentAchievementsCount,
                        String redditUrl,
                        String redditName,
                        String redditDescription,
                        String redditLogo,
                        int redditCount,
                        int twitchCount,
                        int youtubeCount,
                        int reviewsTextCount,
                        int ratingsCount,
                        int suggestionsCount,
                        List<String> alternativeNames,
                        String metacriticUrl,
                        int parentsCount,
                        int additionsCount,
                        int gameSeriesCount,
                        int reviewsCount,
                        String saturatedColor,
                        String dominantColor,
                        List<PlatformResponse> platforms,
                        List<StoreResponse> stores,
                        List<DeveloperResponse> developers,
                        List<GenreResponse> genres,
                        List<TagResponse> tags,
                        List<PublisherResponse> publishers,
                        EsrbResponse esrbRating,
                        List<ScreenshotResponse> shortScreenshots) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.nameOriginal = nameOriginal;
        this.description = description;
        this.metacritic = metacritic;
        this.released = released;
        this.tba = tba;
        this.updated = updated;
        this.backgroundImage = backgroundImage;
        this.backgroundImageAdditional = backgroundImageAdditional;
        this.website = website;
        this.rating = rating;
        this.ratingTop = ratingTop;
        this.ratings = ratings;
        this.added = added;
        this.playtime = playtime;
        this.screenshotsCount = screenshotsCount;
        this.moviesCount = moviesCount;
        this.creatorsCount = creatorsCount;
        this.achievementsCount = achievementsCount;
        this.parentAchievementsCount = parentAchievementsCount;
        this.redditUrl = redditUrl;
        this.redditName = redditName;
        this.redditDescription = redditDescription;
        this.redditLogo = redditLogo;
        this.redditCount = redditCount;
        this.twitchCount = twitchCount;
        this.youtubeCount = youtubeCount;
        this.reviewsTextCount = reviewsTextCount;
        this.ratingsCount = ratingsCount;
        this.suggestionsCount = suggestionsCount;
        this.alternativeNames = alternativeNames;
        this.metacriticUrl = metacriticUrl;
        this.parentsCount = parentsCount;
        this.additionsCount = additionsCount;
        this.gameSeriesCount = gameSeriesCount;
        this.reviewsCount = reviewsCount;
        this.saturatedColor = saturatedColor;
        this.dominantColor = dominantColor;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOriginal() {
        return nameOriginal;
    }

    public void setNameOriginal(String nameOriginal) {
        this.nameOriginal = nameOriginal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMetacritic() {
        return metacritic;
    }

    public void setMetacritic(int metacritic) {
        this.metacritic = metacritic;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public boolean isTba() {
        return tba;
    }

    public void setTba(boolean tba) {
        this.tba = tba;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
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

    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    public int getScreenshotsCount() {
        return screenshotsCount;
    }

    public void setScreenshotsCount(int screenshotsCount) {
        this.screenshotsCount = screenshotsCount;
    }

    public int getMoviesCount() {
        return moviesCount;
    }

    public void setMoviesCount(int moviesCount) {
        this.moviesCount = moviesCount;
    }

    public int getCreatorsCount() {
        return creatorsCount;
    }

    public void setCreatorsCount(int creatorsCount) {
        this.creatorsCount = creatorsCount;
    }

    public int getAchievementsCount() {
        return achievementsCount;
    }

    public void setAchievementsCount(int achievementsCount) {
        this.achievementsCount = achievementsCount;
    }

    public int getParentAchievementsCount() {
        return parentAchievementsCount;
    }

    public void setParentAchievementsCount(int parentAchievementsCount) {
        this.parentAchievementsCount = parentAchievementsCount;
    }

    public String getRedditUrl() {
        return redditUrl;
    }

    public void setRedditUrl(String redditUrl) {
        this.redditUrl = redditUrl;
    }

    public String getRedditName() {
        return redditName;
    }

    public void setRedditName(String redditName) {
        this.redditName = redditName;
    }

    public String getRedditDescription() {
        return redditDescription;
    }

    public void setRedditDescription(String redditDescription) {
        this.redditDescription = redditDescription;
    }

    public String getRedditLogo() {
        return redditLogo;
    }

    public void setRedditLogo(String redditLogo) {
        this.redditLogo = redditLogo;
    }

    public int getRedditCount() {
        return redditCount;
    }

    public void setRedditCount(int redditCount) {
        this.redditCount = redditCount;
    }

    public int getTwitchCount() {
        return twitchCount;
    }

    public void setTwitchCount(int twitchCount) {
        this.twitchCount = twitchCount;
    }

    public int getYoutubeCount() {
        return youtubeCount;
    }

    public void setYoutubeCount(int youtubeCount) {
        this.youtubeCount = youtubeCount;
    }

    public int getReviewsTextCount() {
        return reviewsTextCount;
    }

    public void setReviewsTextCount(int reviewsTextCount) {
        this.reviewsTextCount = reviewsTextCount;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public int getSuggestionsCount() {
        return suggestionsCount;
    }

    public void setSuggestionsCount(int suggestionsCount) {
        this.suggestionsCount = suggestionsCount;
    }

    public List<String> getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(List<String> alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    public String getMetacriticUrl() {
        return metacriticUrl;
    }

    public void setMetacriticUrl(String metacriticUrl) {
        this.metacriticUrl = metacriticUrl;
    }

    public int getParentsCount() {
        return parentsCount;
    }

    public void setParentsCount(int parentsCount) {
        this.parentsCount = parentsCount;
    }

    public int getAdditionsCount() {
        return additionsCount;
    }

    public void setAdditionsCount(int additionsCount) {
        this.additionsCount = additionsCount;
    }

    public int getGameSeriesCount() {
        return gameSeriesCount;
    }

    public void setGameSeriesCount(int gameSeriesCount) {
        this.gameSeriesCount = gameSeriesCount;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(int reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public String getSaturatedColor() {
        return saturatedColor;
    }

    public void setSaturatedColor(String saturatedColor) {
        this.saturatedColor = saturatedColor;
    }

    public String getDominantColor() {
        return dominantColor;
    }

    public void setDominantColor(String dominantColor) {
        this.dominantColor = dominantColor;
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