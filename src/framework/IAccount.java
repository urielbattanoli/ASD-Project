package framework;

import framework.Holder.AccountHolder;
import framework.Strategy.IStrategyFactory;

import java.util.List;

public interface IAccount {
    String getId();
    void setId(String id);
    double getBalance();
    void increase(double amount, String description);
    void deduct(double amount, String description);
    void transferFunds(IAccount toAccount, double amount, String description);
    void addEntry(Entry entry);
    AccountHolder getHolder();
    void setHolder(AccountHolder holder);
    List<Entry> getEntryList();
    void addInterest();
    void setFactory(IStrategyFactory factory);
    public double getMinimumPayment();
    public double getMonthlyInterest();
}
