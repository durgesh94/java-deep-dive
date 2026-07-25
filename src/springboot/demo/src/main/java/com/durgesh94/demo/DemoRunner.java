package com.durgesh94.demo;

import com.durgesh94.demo.payment.PaymentGateway;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoRunner implements ApplicationRunner {

    private final PaymentGateway paymentGateway;

    public DemoRunner(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception{
        paymentGateway.print();
    }
}
