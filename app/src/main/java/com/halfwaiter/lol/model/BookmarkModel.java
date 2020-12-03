package com.halfwaiter.lol.model;

public class BookmarkModel {
    private String soundNameBookmark, soundLengthBookmark;
    private boolean isReactedBookmark;

    public BookmarkModel() {
        this.soundNameBookmark = soundNameBookmark;
        this.soundLengthBookmark = soundLengthBookmark;
        this.isReactedBookmark = isReactedBookmark;
    }

    public String getSoundNameBookmark() {
        return soundNameBookmark;
    }

    public void setSoundNameBookmark(String soundNameBookmark) {
        this.soundNameBookmark = soundNameBookmark;
    }

    public String getSoundLengthBookmark() {
        return soundLengthBookmark;
    }

    public void setSoundLengthBookmark(String soundLengthBookmark) {
        this.soundLengthBookmark = soundLengthBookmark;
    }

    public boolean isReactedBookmark() {
        return isReactedBookmark;
    }

    public void setReactedBookmark(boolean reactedBookmark) {
        isReactedBookmark = reactedBookmark;
    }
}
