package corejava.interfaces;

public class UPIPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Processing UPI payment of amount: " + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("Processing UPI refund of amount: " + amount);
    }

    @Override
    public void checkStatus() {
        System.out.println("Checking UPI payment status");
    }
}
