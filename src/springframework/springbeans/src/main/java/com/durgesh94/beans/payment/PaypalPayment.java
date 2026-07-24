package com.durgesh94.beans.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("PAYPAL") // @Qualifier annotation is used to specify which bean should be injected when there are multiple beans of the same type. It helps to disambiguate the dependency injection process by providing a unique identifier for the bean. In this case, it indicates that this particular implementation of PaymentService should be used when the "PAYPAL" qualifier is specified during injection.
public class PaypalPayment implements PaymentService {
    @Override
    public void pay(){
        System.out.println("Payment via paypal successfully done!");
    }
}
