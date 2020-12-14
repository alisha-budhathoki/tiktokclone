package com.halfwaiter.lol.model;

import java.util.List;

public class ExploreModel {
    private boolean isPlaying;

    private String soundName, soundLength, soundUrl;
    private boolean isReacted;

    public ExploreModel() {
        this.isPlaying = isPlaying;
        this.soundName = soundName;
        this.soundLength = soundLength;
        this.soundUrl = soundUrl;
        this.isReacted = isReacted;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    public String getSoundLength() {
        return soundLength;
    }

    public void setSoundLength(String soundLength) {
        this.soundLength = soundLength;
    }

    public String getSoundUrl() {
        return soundUrl;
    }

    public void setSoundUrl(String soundUrl) {
        this.soundUrl = soundUrl;
    }

    public boolean isReacted() {
        return isReacted;
    }

    public void setReacted(boolean reacted) {
        isReacted = reacted;
    }
}
