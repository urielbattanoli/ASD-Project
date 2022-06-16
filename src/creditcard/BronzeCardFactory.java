package creditcard;

import framework.Strategy.IInterestStrategy;
import framework.Strategy.IMinimumPaymentStrategy;
import framework.Strategy.IStrategyFactory;

public class BronzeCardFactory implements IStrategyFactory {

    private static IStrategyFactory instance = new BronzeCardFactory();

    private BronzeCardFactory() {}

    public IMinimumPaymentStrategy paymentStrategy() {
        return () -> .1;
    }

    public IInterestStrategy interestStrategy() {
        return (balance) -> .06;
    }

    public static IStrategyFactory getInstance() {
        return instance;
    }
}
