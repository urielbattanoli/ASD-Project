package creditcard;

import framework.IInterestStrategy;
import framework.IMinimumPaymentStrategy;
import framework.IStrategyFactory;

public class SilverCardFactory implements IStrategyFactory {

    private static IStrategyFactory instance = new SilverCardFactory();

    private SilverCardFactory() {}

    public IMinimumPaymentStrategy paymentStrategy() {
        return (balance) -> balance * .14;
    }

    public IInterestStrategy InterestStrategy() {
        return (balance) -> balance * .1;
    }

    public static IStrategyFactory getInstance() {
        return instance;
    }
}
