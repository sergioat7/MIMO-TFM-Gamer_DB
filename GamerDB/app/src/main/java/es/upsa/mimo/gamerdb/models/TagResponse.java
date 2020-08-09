package es.upsa.mimo.gamerdb.models;

public class TagResponse {

    private int id;
    private String name;
    private String slug;
    private int games_count;
    private String image_background;

    public TagResponse(int id,
                       String name,
                       String slug,
                       int games_count,
                       String image_background) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.games_count = games_count;
        this.image_background = image_background;
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

    public int getGames_count() {
        return games_count;
    }

    public void setGames_count(int games_count) {
        this.games_count = games_count;
    }

    public String getImage_background() {
        return image_background;
    }

    public void setImage_background(String image_background) {
        this.image_background = image_background;
    }
}
