package framework;

import java.util.List;

public interface DAO {

    void saveAccount(Account account);
    void updateAccount(Account account);
    Account loadAccount(String id);
    List<Account> getAccounts();
}
