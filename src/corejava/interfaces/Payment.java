package corejava.interfaces;

// must be implemented by any class that implements this interface

public interface Payment {
    void pay(double amount);

    void refund(double amount);

    void checkStatus();
}
