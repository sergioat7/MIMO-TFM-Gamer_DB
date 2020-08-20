package es.upsa.mimo.gamerdb.models;

public class ScreenshotResponse {

    private int id;
    private String image;

    public ScreenshotResponse(int id,
                              String image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
