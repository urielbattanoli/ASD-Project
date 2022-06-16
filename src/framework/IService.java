package framework;

import framework.Observer.IObserver;
import framework.Observer.ISubject;
import framework.ReportGenerator.IReportGenerator;

public interface IService extends ISubject {

    void setDao(ServiceDAO dao);
    void setGenerator(IReportGenerator generator);
    void saveAccount(IAccount account);
    double getAccountBalance(String id);
    void increaseAmount(String id, double amount, String description);
    void deductAmount(String id, double amount, String description);
    void transfer(String fromId, String toId, double amount, String description);
    void addInterest();
    String generateReport();
    void addObserver(IObserver o);
    void removeObserver(IObserver o);
}
