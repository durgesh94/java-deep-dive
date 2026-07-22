package corejava.interfaces;

public class CreditCardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Processing credit card payment of amount: " + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("Processing credit card refund of amount: " + amount);
    }

    @Override
    public void checkStatus() {
        System.out.println("Checking credit card payment status");
    }
}