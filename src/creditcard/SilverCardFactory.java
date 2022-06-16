package creditcard;

import framework.Strategy.IInterestStrategy;
import framework.Strategy.IMinimumPaymentStrategy;
import framework.Strategy.IStrategyFactory;

public class SilverCardFactory implements IStrategyFactory {

    private static IStrategyFactory instance = new SilverCardFactory();

    private SilverCardFactory() {}

    public IMinimumPaymentStrategy paymentStrategy() {
        return () -> .14;
    }

    public IInterestStrategy interestStrategy() {
        return (balance) -> .1;
    }

    public static IStrategyFactory getInstance() {
        return instance;
    }
}
