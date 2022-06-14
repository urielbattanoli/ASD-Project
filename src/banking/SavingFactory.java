package banking;

import framework.IInterestStrategy;
import framework.IMinimumPaymentStrategy;
import framework.IStrategyFactory;

public class SavingFactory implements IStrategyFactory {

    public IMinimumPaymentStrategy paymentStrategy() {
        return (balance) -> balance * .1;
    }

    public IInterestStrategy InterestStrategy() {
        return (balance) -> balance < 1000 ? balance * .01 : balance > 5000 ? balance * .04 : balance * .02;
    }
}