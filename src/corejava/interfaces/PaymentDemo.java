package corejava.interfaces;

public class PaymentDemo {
    public static void main(String[] args) {
        Payment creditCardPayment = new CreditCardPayment();
        creditCardPayment.pay(100.0);
        creditCardPayment.refund(50.0);
        creditCardPayment.checkStatus();

        Payment walletPayment = new WalletPayment();
        walletPayment.pay(200.0);
        walletPayment.refund(75.0);
        walletPayment.checkStatus();

        Payment upiPayment = new UPIPayment();
        upiPayment.pay(300.0);
        upiPayment.refund(100.0);
        upiPayment.checkStatus();
    }
}
