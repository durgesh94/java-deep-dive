package com.durgesh94.core.notification;

public class PushNotificationService implements NotificationService{
    @Override
    public void sendNotification() {
        System.out.println("Push notification send successfully.");
    }
}
