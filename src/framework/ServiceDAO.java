package framework;

import java.util.ArrayList;
import java.util.Collection;

public class ServiceDAO {

    private Collection<Account> accountList = new ArrayList<Account>();

    public void saveAccount(Account account) {
        accountList.add(account);
    }

    public void updateAccount(Account account) {
        Account accountExist = loadAccount(account.getId());
        if (accountExist != null) {
            accountList.remove(accountExist);
            accountList.add(account);
        }
    }

    public Account loadAccount(String id) {
        for (Account account : accountList) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    public Collection<Account> getAccounts() {
        return accountList;
    }
}
