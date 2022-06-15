package banking;

import framework.Strategy.IInterestStrategy;
import framework.Strategy.IMinimumPaymentStrategy;
import framework.Strategy.IStrategyFactory;

public class CheckingFactory implements IStrategyFactory {

    private static IStrategyFactory instance = new CheckingFactory();

    private CheckingFactory() {}

    public IMinimumPaymentStrategy paymentStrategy() {
        return (balance) -> balance * .1;
    }

    public IInterestStrategy interestStrategy() {
        return (balance) -> balance < 1000 ? balance * .015 : balance * .025;
    }

    public static IStrategyFactory getInstance() {
        return instance;
    }
}
