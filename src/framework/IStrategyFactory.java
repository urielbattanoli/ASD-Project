package framework;

public interface IStrategyFactory {
    IMinimumPaymentStrategy paymentStrategy();
    IInterestStrategy InterestStrategy();
}
