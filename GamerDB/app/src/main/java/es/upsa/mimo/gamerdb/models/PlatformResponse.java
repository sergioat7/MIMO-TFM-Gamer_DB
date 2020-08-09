package es.upsa.mimo.gamerdb.models;

public class PlatformResponse {

    private PlatformObjectResponse platform;
    private String released_at;
    private RequirementResponse requirements;

    public PlatformResponse(PlatformObjectResponse platform,
                            String released_at,
                            RequirementResponse requirements) {
        this.platform = platform;
        this.released_at = released_at;
        this.requirements = requirements;
    }

    public PlatformObjectResponse getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformObjectResponse platform) {
        this.platform = platform;
    }

    public String getReleased_at() {
        return released_at;
    }

    public void setReleased_at(String released_at) {
        this.released_at = released_at;
    }

    public RequirementResponse getRequirements() {
        return requirements;
    }

    public void setRequirements(RequirementResponse requirements) {
        this.requirements = requirements;
    }
}
