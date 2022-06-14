package creditcard;

import framework.IInterestStrategy;
import framework.IMinimumPaymentStrategy;
import framework.IStrategyFactory;

public class GoldCardFactory implements IStrategyFactory {

    public IMinimumPaymentStrategy paymentStrategy() {
        return (balance) -> balance * .12;
    }

    public IInterestStrategy InterestStrategy() {
        return (balance) -> balance * .08;
    }
}
