package springframework.springcore.src.main.java.com.durgesh94.core.notification;

public class SMSService implements NotificationService {
    @Override
    public void sendNotification() {
        System.out.println("SMS send successfully.");
    }
}
