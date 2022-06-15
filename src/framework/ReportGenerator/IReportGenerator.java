package framework.ReportGenerator;

import framework.Account;

import java.util.List;

public interface IReportGenerator {
    String generateReport(List<Account> list);
}
