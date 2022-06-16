package banking;

import framework.Strategy.IInterestStrategy;
import framework.Strategy.IMinimumPaymentStrategy;
import framework.Strategy.IStrategyFactory;

public class SavingFactory implements IStrategyFactory {

    private static IStrategyFactory instance = new SavingFactory();

    private SavingFactory() {}

    public IMinimumPaymentStrategy paymentStrategy() {
        return () -> .1;
    }

    public IInterestStrategy interestStrategy() {
        return (balance) -> balance < 1000 ? .01 : balance > 5000 ? .04 : .02;
    }

    public static IStrategyFactory getInstance() {
        return instance;
    }
}
