package com.durgesh94.beans.order;

import com.durgesh94.beans.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // @Component annotation is used to indicate that a class is a Spring component. It tells Spring that this class should be managed as a bean in the application context. When Spring scans the classpath for components, it will detect this class and create an instance of it as a bean, allowing it to be injected into other components or services as needed.
public class OrderService {

    // 3. Field Injection (final not allowed) // hard to test, can't be final (Avoid)
//    @Autowired
//    public PaymentService payment;

    public final PaymentService payment;

    // 1. Constructor Injection (Recommended approach)
   // @Autowired (Not mandatory annotation to mention for constructor injection)
   public OrderService(@Qualifier("UPI") PaymentService payment){
        this.payment = payment;
   }

    // 2. Setter Injection
//    @Autowired (Mandatory annotation to mention for setter injector)
//    public void setPayment(PaymentService payment) {
//        this.payment = payment;
//    }

    public void placeOrder(){
        payment.pay();
        System.out.println("Order placed successfully.");
    }
}
