package com.halfwaiter.lol.model;

public class NotificationModel {
    int notificationPic;
    String notificationTitle, notificationContent;

    public NotificationModel() {
        this.notificationPic = notificationPic;
        this.notificationTitle = notificationTitle;
        this.notificationContent = notificationContent;
    }

    public int getNotificationPic() {
        return notificationPic;
    }

    public void setNotificationPic(int notificationPic) {
        this.notificationPic = notificationPic;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }
}
