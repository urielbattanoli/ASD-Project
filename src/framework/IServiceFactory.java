package framework;

import framework.ReportGenerator.IReportGenerator;

public interface IServiceFactory {

    DAO createDAO();
    IReportGenerator createGenerator();
    IAccountCreator createAccountCreator();
}
