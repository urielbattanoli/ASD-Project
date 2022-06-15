package creditcard;

import framework.Strategy.IInterestStrategy;
import framework.Strategy.IMinimumPaymentStrategy;
import framework.Strategy.IStrategyFactory;

public class GoldCardFactory implements IStrategyFactory {

    private static IStrategyFactory instance = new GoldCardFactory();

    private GoldCardFactory() {}

    public IMinimumPaymentStrategy paymentStrategy() {
        return (balance) -> balance * .12;
    }

    public IInterestStrategy InterestStrategy() {
        return (balance) -> balance * .08;
    }

    public static IStrategyFactory getInstance() {
        return instance;
    }
}
