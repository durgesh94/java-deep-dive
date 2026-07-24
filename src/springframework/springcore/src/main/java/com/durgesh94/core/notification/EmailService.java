package com.durgesh94.core.notification;

public class EmailService implements NotificationService {
    @Override
    public void sendNotification() {
        System.out.println("Email send successfully.");
    }
}
