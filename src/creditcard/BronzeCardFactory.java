package creditcard;

import framework.IInterestStrategy;
import framework.IMinimumPaymentStrategy;
import framework.IStrategyFactory;

public class BronzeCardFactory implements IStrategyFactory {

    private static IStrategyFactory instance = new BronzeCardFactory();

    private BronzeCardFactory() {}

    public IMinimumPaymentStrategy paymentStrategy() {
        return (balance) -> balance * .1;
    }

    public IInterestStrategy InterestStrategy() {
        return (balance) -> balance * .06;
    }

    public static IStrategyFactory getInstance() {
        return instance;
    }
}
