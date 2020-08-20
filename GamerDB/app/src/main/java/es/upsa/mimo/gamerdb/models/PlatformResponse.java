package es.upsa.mimo.gamerdb.models;

import com.google.gson.annotations.SerializedName;

public class PlatformResponse {

    private PlatformObjectResponse platform;
    @SerializedName("released_at")
    private String releasedAt;
    private RequirementResponse requirements;

    public PlatformResponse(PlatformObjectResponse platform,
                            String releasedAt,
                            RequirementResponse requirements) {
        this.platform = platform;
        this.releasedAt = releasedAt;
        this.requirements = requirements;
    }

    public PlatformObjectResponse getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformObjectResponse platform) {
        this.platform = platform;
    }

    public String getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(String releasedAt) {
        this.releasedAt = releasedAt;
    }

    public RequirementResponse getRequirements() {
        return requirements;
    }

    public void setRequirements(RequirementResponse requirements) {
        this.requirements = requirements;
    }
}
