package com.durgesh94.demo.payment;

import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {

    // Option 3: Construction Injection
    private PaymentProperties paymentProperties;

    public PaymentGateway(PaymentProperties paymentProperties){
        this.paymentProperties = paymentProperties;
    }

    // Option 2: like autowired
//    @Value("${payment-property.type}")
//    private String type;
//    @Value("${payment-property.retry}")
//    private int retry;

    // Option 1: Getting property values by constructor
//    public PaymentProperties(@Value("${payment-property.type}") String type, @Value("${payment-property.retry}") int retry){
//        this.type = type;
//        this.retry = retry;
//    }

    public String getType() {
        return paymentProperties.getType();
    }

//    public void setType(String type) {
//        this.type = type;
//    }

    public int getRetry() {
        return paymentProperties.getRetry();
    }

//    public void setRetry(int retry) {
//        this.retry = retry;
//    }

    public void print(){
        System.out.println(paymentProperties.getType());
        System.out.println(paymentProperties.getRetry());
    }
}
