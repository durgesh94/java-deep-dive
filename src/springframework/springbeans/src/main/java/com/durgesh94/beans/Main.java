package com.durgesh94.beans;

import com.durgesh94.beans.order.OrderService;
import com.durgesh94.beans.user.User;
import com.durgesh94.core.notification.PushNotificationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        // Create the Spring application context using the AppConfig class
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        // Get the OrderService bean from the Spring context
        OrderService orderService = context.getBean(OrderService.class);
        orderService.placeOrder();

        // Get the PushNotificationService bean from the Spring context: its from other module, but we can use it in beans module because beans module has dependency on core module
        PushNotificationService pushNotificationService = context.getBean(PushNotificationService.class);
        pushNotificationService.sendNotification();

        // Get the User bean from the Spring context: its from beans module, but we can use it in beans module because beans module has dependency on core module
        User user = context.getBean(User.class);
        System.out.println(user.getFirstName() + " " + user.getLastName());

    }
}