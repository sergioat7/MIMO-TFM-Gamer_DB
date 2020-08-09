package es.upsa.mimo.gamerdb.models;

public class RatingResponse {

    private int id;
    private String title;
    private int count;
    private double percent;

    public RatingResponse(int id,
                          String title,
                          int count,
                          double percent) {
        this.id = id;
        this.title = title;
        this.count = count;
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
