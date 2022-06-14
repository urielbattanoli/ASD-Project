package creditcard;

import framework.IInterestStrategy;
import framework.IMinimumPaymentStrategy;
import framework.IStrategyFactory;

public class SilverCardFactory implements IStrategyFactory {

    public IMinimumPaymentStrategy paymentStrategy() {
        return (balance) -> balance * .14;
    }

    public IInterestStrategy InterestStrategy() {
        return (balance) -> balance * .1;
    }
}
