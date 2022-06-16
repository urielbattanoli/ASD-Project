package framework;

import framework.Holder.AccountHolder;
import framework.Strategy.IMinimumPaymentStrategy;
import framework.Strategy.IStrategyFactory;

import java.util.ArrayList;
import java.util.List;

public class Account implements IAccount {

    private String id;
    private AccountHolder holder;
    private List<Entry> entryList = new ArrayList<>();
    private IStrategyFactory factory;

    public Account(String id, AccountHolder holder, IStrategyFactory factory) {
        this.id = id;
        this.holder = holder;
        this.factory = factory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return entryList
                .stream()
                .reduce(0.0, (subtotal, entry) -> subtotal + entry.getAmount(), Double::sum);
    }

    public void increase(double amount, String description) {
        Entry entry = new Entry(amount, description, "", "");
        entryList.add(entry);
    }

    public void deduct(double amount, String description) {
        Entry entry = new Entry(-amount, description, "", "");
        entryList.add(entry);
    }

    public void transferFunds(IAccount toAccount, double amount, String description) {
        Entry fromEntry = new Entry(-amount, description, toAccount.getId(), toAccount.getHolder().getName());
        Entry toEntry = new Entry(amount, description, getId(), getHolder().getName());

        entryList.add(fromEntry);
        toAccount.addEntry(toEntry);
    }

    public void addEntry(Entry entry) {
        entryList.add(entry);
    }

    public AccountHolder getHolder() {
        return holder;
    }

    public void setHolder(AccountHolder holder) {
        this.holder = holder;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void addInterest() {
        double balance = getBalance();
        double amount = factory.interestStrategy().calculateInterest(balance);
        Entry entry = new Entry(amount, "interest", "", "");
        entryList.add(entry);
    }
    public double getMinimumPayment(){
        return factory.paymentStrategy().calculatePayment(this.getBalance());
    }
    public double getMonthlyInterest(){
        return factory.interestStrategy().calculateInterest(this.getBalance());
    }
    public void setFactory(IStrategyFactory factory) {
        this.factory = factory;
    }
    public IStrategyFactory getFactory(IStrategyFactory factory) {
        return factory;
    }
}
