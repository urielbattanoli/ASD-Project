package framework;

import framework.Holder.AccountHolder;
import framework.Observer.IObserver;
import framework.Observer.ISubject;
import framework.ReportGenerator.*;
import framework.Strategy.IStrategyFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Service implements ISubject {

    private ServiceDAO dao = new ServiceDAO();
    private IReportGenerator generator = new ReportGenerator();
    private List<IObserver> observers = new ArrayList<>();

    public void setDao(ServiceDAO dao) {
        this.dao = dao;
    }

    public void setGenerator(IReportGenerator generator) {
        this.generator = generator;
    }

    public Account createAccount(String id, AccountHolder holder, IStrategyFactory factory) {
        Account account = new Account(id, holder, factory);
        dao.saveAccount(account);
        return account;
    }

    public Account createAccount(String id, AccountHolder holder, IStrategyFactory factory, String expDate) {
        Account account = new CreditAccount(id, holder, factory, expDate);
        dao.saveAccount(account);
        return account;
    }

    public double getAccountBalance(String id) {
        Account account = dao.loadAccount(id);
        return account.getBalance();
    }

    public void increaseAmount(String id, double amount, String description) {
        Account account = dao.loadAccount(id);
        account.increase(amount, description);
        dao.updateAccount(account);
        observers.forEach(observer -> observer.increaseDone(account));
    }

    public void deductAmount(String id, double amount, String description) {
        Account account = dao.loadAccount(id);
        account.deduct(amount, description);
        dao.updateAccount(account);
        observers.forEach(observer -> observer.deductDone(account));
    }

    public void transfer(String fromId, String toId, double amount, String description) {
        Account fromAccount = dao.loadAccount(fromId);
        Account toAccount = dao.loadAccount(toId);
        fromAccount.transferFunds(toAccount, amount, description);
        dao.updateAccount(fromAccount);
        dao.updateAccount(toAccount);
        observers.forEach(observer -> observer.transferDone(fromAccount));
        observers.forEach(observer -> observer.transferDone(toAccount));
    }

    public void addInterest() {
        Collection<Account> list = dao.getAccounts();
        Account[] accounts = list.toArray(new Account[list.size()]);
        for (Account account : accounts) {
            account.addInterest();
            dao.updateAccount(account);
        }
    }

    public String generateReport() {
       return generator.generateReport(dao.getAccounts());
    }

    public void addObserver(IObserver o) {
        observers.add(o);
    }

    public void removeObserver(IObserver o) {
        observers.remove(o);
    }
}
