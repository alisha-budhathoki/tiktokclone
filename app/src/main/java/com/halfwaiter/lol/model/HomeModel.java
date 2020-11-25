package com.halfwaiter.lol.model;

public class HomeModel {
int  userImage, lolVideo;
String username, caption, soundName, noLikes, noComments ;

    public HomeModel() {
        this.lolVideo = lolVideo;
        this.userImage = userImage;
        this.username = username;
        this.caption = caption;
        this.soundName = soundName;
        this.noLikes = noLikes;
        this.noComments = noComments;
    }

    public int getLolVideo() {
        return lolVideo;
    }

    public void setLolVideo(int lolVideo) {
        this.lolVideo = lolVideo;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    public String getNoLikes() {
        return noLikes;
    }

    public void setNoLikes(String noLikes) {
        this.noLikes = noLikes;
    }

    public String getNoComments() {
        return noComments;
    }

    public void setNoComments(String noComments) {
        this.noComments = noComments;
    }
}
