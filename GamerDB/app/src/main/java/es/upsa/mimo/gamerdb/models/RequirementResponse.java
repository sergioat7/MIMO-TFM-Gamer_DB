package es.upsa.mimo.gamerdb.models;

class RequirementResponse {

    private String minimum;
    private String recommended;

    public RequirementResponse(String minimum,
                               String recommended) {
        this.minimum = minimum;
        this.recommended = recommended;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }
}
