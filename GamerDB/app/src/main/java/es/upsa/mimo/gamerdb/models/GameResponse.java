package es.upsa.mimo.gamerdb.models;

import java.util.List;

public class GameResponse {

    private int id;
    private String slug;
    private String name;
    private String name_original;
    private String description;
    private int metacritic;
    private String released;
    private boolean tba;
    private String updated;
    private String background_image;
    private String background_image_additional;
    private String website;
    private double rating;
    private double rating_top;
    private List<RatingResponse> ratings;
    private int added;
    private int playtime;
    private int screenshots_count;
    private int movies_count;
    private int creators_count;
    private int achievements_count;
    private int parent_achievements_count;
    private String reddit_url;
    private String reddit_name;
    private String reddit_description;
    private String reddit_logo;
    private int reddit_count;
    private int twitch_count;
    private int youtube_count;
    private int reviews_text_count;
    private int ratings_count;
    private int suggestions_count;
    private List<String> alternative_names;
    private String metacritic_url;
    private int parents_count;
    private int additions_count;
    private int game_series_count;
    private int reviews_count;
    private String saturated_color;
    private String dominant_color;
    private List<PlatformResponse> platforms;
    private List<StoreResponse> stores;
    private List<DeveloperResponse> developers;
    private List<GenreResponse> genres;
    private List<TagResponse> tags;
    private List<PublisherResponse> publishers;
    private EsrbResponse esrb_rating;

    public GameResponse(int id,
                        String slug,
                        String name,
                        String name_original,
                        String description,
                        int metacritic,
                        String released,
                        boolean tba,
                        String updated,
                        String background_image,
                        String background_image_additional,
                        String website,
                        double rating,
                        double rating_top,
                        List<RatingResponse> ratings,
                        int added,
                        int playtime,
                        int screenshots_count,
                        int movies_count,
                        int creators_count,
                        int achievements_count,
                        int parent_achievements_count,
                        String reddit_url,
                        String reddit_name,
                        String reddit_description,
                        String reddit_logo,
                        int reddit_count,
                        int twitch_count,
                        int youtube_count,
                        int reviews_text_count,
                        int ratings_count,
                        int suggestions_count,
                        List<String> alternative_names,
                        String metacritic_url,
                        int parents_count,
                        int additions_count,
                        int game_series_count,
                        int reviews_count,
                        String saturated_color,
                        String dominant_color,
                        List<PlatformResponse> platforms,
                        List<StoreResponse> stores,
                        List<DeveloperResponse> developers,
                        List<GenreResponse> genres,
                        List<TagResponse> tags,
                        List<PublisherResponse> publishers,
                        EsrbResponse esrb_rating) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.name_original = name_original;
        this.description = description;
        this.metacritic = metacritic;
        this.released = released;
        this.tba = tba;
        this.updated = updated;
        this.background_image = background_image;
        this.background_image_additional = background_image_additional;
        this.website = website;
        this.rating = rating;
        this.rating_top = rating_top;
        this.ratings = ratings;
        this.added = added;
        this.playtime = playtime;
        this.screenshots_count = screenshots_count;
        this.movies_count = movies_count;
        this.creators_count = creators_count;
        this.achievements_count = achievements_count;
        this.parent_achievements_count = parent_achievements_count;
        this.reddit_url = reddit_url;
        this.reddit_name = reddit_name;
        this.reddit_description = reddit_description;
        this.reddit_logo = reddit_logo;
        this.reddit_count = reddit_count;
        this.twitch_count = twitch_count;
        this.youtube_count = youtube_count;
        this.reviews_text_count = reviews_text_count;
        this.ratings_count = ratings_count;
        this.suggestions_count = suggestions_count;
        this.alternative_names = alternative_names;
        this.metacritic_url = metacritic_url;
        this.parents_count = parents_count;
        this.additions_count = additions_count;
        this.game_series_count = game_series_count;
        this.reviews_count = reviews_count;
        this.saturated_color = saturated_color;
        this.dominant_color = dominant_color;
        this.platforms = platforms;
        this.stores = stores;
        this.developers = developers;
        this.genres = genres;
        this.tags = tags;
        this.publishers = publishers;
        this.esrb_rating = esrb_rating;
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

    public String getName_original() {
        return name_original;
    }

    public void setName_original(String name_original) {
        this.name_original = name_original;
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

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public String getBackground_image_additional() {
        return background_image_additional;
    }

    public void setBackground_image_additional(String background_image_additional) {
        this.background_image_additional = background_image_additional;
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

    public double getRating_top() {
        return rating_top;
    }

    public void setRating_top(double rating_top) {
        this.rating_top = rating_top;
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

    public int getScreenshots_count() {
        return screenshots_count;
    }

    public void setScreenshots_count(int screenshots_count) {
        this.screenshots_count = screenshots_count;
    }

    public int getMovies_count() {
        return movies_count;
    }

    public void setMovies_count(int movies_count) {
        this.movies_count = movies_count;
    }

    public int getCreators_count() {
        return creators_count;
    }

    public void setCreators_count(int creators_count) {
        this.creators_count = creators_count;
    }

    public int getAchievements_count() {
        return achievements_count;
    }

    public void setAchievements_count(int achievements_count) {
        this.achievements_count = achievements_count;
    }

    public int getParent_achievements_count() {
        return parent_achievements_count;
    }

    public void setParent_achievements_count(int parent_achievements_count) {
        this.parent_achievements_count = parent_achievements_count;
    }

    public String getReddit_url() {
        return reddit_url;
    }

    public void setReddit_url(String reddit_url) {
        this.reddit_url = reddit_url;
    }

    public String getReddit_name() {
        return reddit_name;
    }

    public void setReddit_name(String reddit_name) {
        this.reddit_name = reddit_name;
    }

    public String getReddit_description() {
        return reddit_description;
    }

    public void setReddit_description(String reddit_description) {
        this.reddit_description = reddit_description;
    }

    public String getReddit_logo() {
        return reddit_logo;
    }

    public void setReddit_logo(String reddit_logo) {
        this.reddit_logo = reddit_logo;
    }

    public int getReddit_count() {
        return reddit_count;
    }

    public void setReddit_count(int reddit_count) {
        this.reddit_count = reddit_count;
    }

    public int getTwitch_count() {
        return twitch_count;
    }

    public void setTwitch_count(int twitch_count) {
        this.twitch_count = twitch_count;
    }

    public int getYoutube_count() {
        return youtube_count;
    }

    public void setYoutube_count(int youtube_count) {
        this.youtube_count = youtube_count;
    }

    public int getReviews_text_count() {
        return reviews_text_count;
    }

    public void setReviews_text_count(int reviews_text_count) {
        this.reviews_text_count = reviews_text_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public int getSuggestions_count() {
        return suggestions_count;
    }

    public void setSuggestions_count(int suggestions_count) {
        this.suggestions_count = suggestions_count;
    }

    public List<String> getAlternative_names() {
        return alternative_names;
    }

    public void setAlternative_names(List<String> alternative_names) {
        this.alternative_names = alternative_names;
    }

    public String getMetacritic_url() {
        return metacritic_url;
    }

    public void setMetacritic_url(String metacritic_url) {
        this.metacritic_url = metacritic_url;
    }

    public int getParents_count() {
        return parents_count;
    }

    public void setParents_count(int parents_count) {
        this.parents_count = parents_count;
    }

    public int getAdditions_count() {
        return additions_count;
    }

    public void setAdditions_count(int additions_count) {
        this.additions_count = additions_count;
    }

    public int getGame_series_count() {
        return game_series_count;
    }

    public void setGame_series_count(int game_series_count) {
        this.game_series_count = game_series_count;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public String getSaturated_color() {
        return saturated_color;
    }

    public void setSaturated_color(String saturated_color) {
        this.saturated_color = saturated_color;
    }

    public String getDominant_color() {
        return dominant_color;
    }

    public void setDominant_color(String dominant_color) {
        this.dominant_color = dominant_color;
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

    public EsrbResponse getEsrb_rating() {
        return esrb_rating;
    }

    public void setEsrb_rating(EsrbResponse esrb_rating) {
        this.esrb_rating = esrb_rating;
    }
}