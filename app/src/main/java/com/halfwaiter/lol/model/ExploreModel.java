package com.halfwaiter.lol.model;

import java.util.List;

public class ExploreModel {
    private boolean isPlaying;

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    private String soundName, soundLength;
    private boolean isReacted;

    public ExploreModel() {
        this.soundName = soundName;
        this.soundLength = soundLength;
        this.isReacted = isReacted;
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

    public boolean isReacted() {
        return isReacted;
    }

    public void setReacted(boolean reacted) {
        isReacted = reacted;
    }
}
