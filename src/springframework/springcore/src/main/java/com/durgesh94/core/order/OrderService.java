package springframework.springcore.src.main.java.com.durgesh94.core.order;

import springframework.springcore.src.main.java.com.durgesh94.core.notification.NotificationService;

public class OrderService {
    NotificationService notification;

    public OrderService(NotificationService notification){
        this.notification = notification;
    }

    public void orderPlaced(){
        System.out.println("Order placed successfully.");
        notification.sendNotification();
    }
}
