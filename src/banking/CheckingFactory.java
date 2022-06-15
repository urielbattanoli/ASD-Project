package banking;

import framework.IInterestStrategy;
import framework.IMinimumPaymentStrategy;
import framework.IStrategyFactory;

public class CheckingFactory implements IStrategyFactory {

    private static IStrategyFactory instance = new CheckingFactory();

    private CheckingFactory() {}

    public IMinimumPaymentStrategy paymentStrategy() {
        return (balance) -> balance * .1;
    }

    public IInterestStrategy InterestStrategy() {
        return (balance) -> balance < 1000 ? balance * .015 : balance * .025;
    }

    public static IStrategyFactory getInstance() {
        return instance;
    }
}
