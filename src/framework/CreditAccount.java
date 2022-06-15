package framework;

import framework.Holder.AccountHolder;
import framework.Strategy.IStrategyFactory;

public class CreditAccount extends Account {

    private String expDate;

    public CreditAccount(String id, AccountHolder holder, IStrategyFactory factory, String expDate) {
        super(id, holder, factory);
        this.expDate = expDate;
    }
}
