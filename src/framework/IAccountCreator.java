package framework;

import framework.Strategy.IStrategyFactory;

public interface IAccountCreator {

    Account createAccount(IStrategyFactory factory);
}
