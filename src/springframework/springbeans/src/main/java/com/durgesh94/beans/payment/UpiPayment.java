package com.durgesh94.beans.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("UPI")
public class UpiPayment implements PaymentService {
    @Override
    public void pay() {
        System.out.println("Payment via UPI successfully done!");
    }
}
