package framework;

public interface IInterestStrategy {
    public double monthlyInterest=0d;
    public double minimumPayment=0d;

    public double calculateInterest();
}
