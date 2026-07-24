package com.durgesh94.core;

import com.durgesh94.core.notification.EmailService;
import com.durgesh94.core.notification.NotificationService;
import com.durgesh94.core.order.OrderService;

public class Main {
    public static void main(String[] args) {
        NotificationService notification = new EmailService();
        OrderService orderService = new OrderService(notification);
        orderService.orderPlaced();
    }
}