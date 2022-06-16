package framework;

import java.util.ArrayList;
import java.util.List;

public class ServiceDAO implements DAO {

    private List<IAccount> accountList = new ArrayList<>();

    public void saveAccount(IAccount account) {
        accountList.add(account);
    }

    public void updateAccount(IAccount account) {
        IAccount accountExist = loadAccount(account.getId());
        if (accountExist != null) {
            accountList.remove(accountExist);
            accountList.add(account);
        }
    }

    public IAccount loadAccount(String id) {
        for (IAccount account : accountList) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    public List<IAccount> getAccounts() {
        return accountList;
    }
}
