package com.halfwaiter.lol.model;

public class CommentModel {
    String userName, userComment, noLikes, userReplies, comntTime;
    int userPhoto;

    public CommentModel() {
        this.userName = userName;
        this.userComment = userComment;
        this.noLikes = noLikes;
        this.userReplies = userReplies;
        this.comntTime = comntTime;
        this.userPhoto = userPhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getNoLikes() {
        return noLikes;
    }

    public void setNoLikes(String noLikes) {
        this.noLikes = noLikes;
    }

    public String getUserReplies() {
        return userReplies;
    }

    public void setUserReplies(String userReplies) {
        this.userReplies = userReplies;
    }

    public String getComntTime() {
        return comntTime;
    }

    public void setComntTime(String comntTime) {
        this.comntTime = comntTime;
    }

    public int getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(int userPhoto) {
        this.userPhoto = userPhoto;
    }
}