package es.upsa.mimo.gamerdb.models;

import es.upsa.mimo.gamerdb.utils.Constants;

public class ClipResponse {

    private String clip;
    private String video;

    public ClipResponse(String clip,
                        String video) {
        this.clip = clip;
        this.video = video;
    }

    public String getClip() {
        return clip;
    }

    public void setClip(String clip) {
        this.clip = clip;
    }

    public String getVideo() {
        return Constants.BASE_YOUTUBE_URL + video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
