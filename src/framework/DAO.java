package framework;

import java.util.List;

public interface DAO {

    void saveAccount(IAccount account);
    void updateAccount(IAccount account);
    IAccount loadAccount(String id);
    List<IAccount> getAccounts();
}
