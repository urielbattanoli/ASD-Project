package framework;

import framework.ReportGenerator.*;

public class ServiceFactory implements IServiceFactory {

    public DAO createDAO() {
        return new ServiceDAO();
    }

    public IReportGenerator createGenerator() {
        return new ReportGenerator();
    }
}
