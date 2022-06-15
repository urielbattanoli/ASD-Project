package framework.Strategy;

public interface IStrategyFactory {
    IMinimumPaymentStrategy paymentStrategy();
    IInterestStrategy InterestStrategy();
}
