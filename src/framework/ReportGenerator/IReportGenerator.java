package framework.ReportGenerator;

import framework.IAccount;

import java.util.List;

public interface IReportGenerator {
    String generateReport(List<IAccount> list);
}
