package springframework.springcore.src.main.java.com.durgesh94.core.order;

import springframework.springcore.src.main.java.com.durgesh94.core.notification.NotificationService;

public class OrderService {
    NotificationService notification;

    // Dependency Injection (DI)
    // Constructor Injection
    // Spring injects dependencies via constructor
    // Spring injects the dependency — OrderService doesn't create it
    public OrderService(NotificationService notification){
        this.notification = notification;
    }

    public void orderPlaced(){
        System.out.println("Order placed successfully.");
        notification.sendNotification();
    }
}
