package es.upsa.mimo.gamerdb.models;

public class PlatformObjectResponse {

    private int id;
    private String name;
    private String slug;
    private String image;
    private String year_end;
    private String year_start;
    private int games_count;
    private String image_background;

    public PlatformObjectResponse(int id,
                                  String name,
                                  String slug,
                                  String image,
                                  String year_end,
                                  String year_start,
                                  int games_count,
                                  String image_background) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.image = image;
        this.year_end = year_end;
        this.year_start = year_start;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getYear_end() {
        return year_end;
    }

    public void setYear_end(String year_end) {
        this.year_end = year_end;
    }

    public String getYear_start() {
        return year_start;
    }

    public void setYear_start(String year_start) {
        this.year_start = year_start;
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
