package framework;

import framework.Observer.IObserver;
import framework.ReportGenerator.*;

import java.util.ArrayList;
import java.util.List;

public class Service implements IService {

    private DAO dao;
    private IReportGenerator generator;
    private List<IObserver> observers = new ArrayList<>();

    public Service(IServiceFactory factory) {
        dao = factory.createDAO();
        generator = factory.createGenerator();
    }

    public void setDao(ServiceDAO dao) {
        this.dao = dao;
    }

    public void setGenerator(IReportGenerator generator) {
        this.generator = generator;
    }

    public void saveAccount(IAccount account) {
        dao.saveAccount(account);
    }

    public double getAccountBalance(String id) {
        IAccount account = dao.loadAccount(id);
        return account.getBalance();
    }

    public void increaseAmount(String id, double amount, String description) {
        IAccount account = dao.loadAccount(id);
        account.increase(amount, description);
        dao.updateAccount(account);
        observers.forEach(observer -> observer.increaseDone(account));
    }

    public void deductAmount(String id, double amount, String description) {
        IAccount account = dao.loadAccount(id);
        account.deduct(amount, description);
        dao.updateAccount(account);
        observers.forEach(observer -> observer.deductDone(account));
    }

    public void transfer(String fromId, String toId, double amount, String description) {
        IAccount fromAccount = dao.loadAccount(fromId);
        IAccount toAccount = dao.loadAccount(toId);
        fromAccount.transferFunds(toAccount, amount, description);
        dao.updateAccount(fromAccount);
        dao.updateAccount(toAccount);
        observers.forEach(observer -> observer.transferDone(fromAccount));
        observers.forEach(observer -> observer.transferDone(toAccount));
    }

    public void addInterest() {
        List<IAccount> list = dao.getAccounts();
        IAccount[] accounts = list.toArray(new Account[list.size()]);
        for (IAccount account : accounts) {
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
