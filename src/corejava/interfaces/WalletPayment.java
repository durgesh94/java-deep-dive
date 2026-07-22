package corejava.interfaces;

public class WalletPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Processing wallet payment of amount: " + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("Processing wallet refund of amount: " + amount);
    }

    @Override
    public void checkStatus() {
        System.out.println("Checking wallet payment status");
    }
}
