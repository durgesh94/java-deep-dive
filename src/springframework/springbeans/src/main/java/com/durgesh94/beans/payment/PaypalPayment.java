package com.durgesh94.beans.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("PAYPAL")
public class PaypalPayment implements PaymentService {
    @Override
    public void pay(){
        System.out.println("Payment via paypal successfully done!");
    }
}
