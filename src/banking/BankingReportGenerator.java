package banking;

import framework.Account;
import framework.AccountHolder;
import framework.Entry;
import framework.IReportGenerator;

import java.util.Collection;

public class BankingReportGenerator implements IReportGenerator {

    public void generateReport(Collection<Account> list) {
        for (Account account : list) {
            AccountHolder customer = account.getHolder();
            System.out.println("Statement for Account: " + account.getId());
            System.out.println("Account Holder: " + customer.getName());

            System.out.println("-Date-------------------------"
                    + "-Description------------------"
                    + "-Amount-------------");

            for (Entry entry : account.getEntryList()) {
                System.out.printf("%30s%30s%20.2f\n",
                        entry.getDate().toString(),
                        entry.getDescription(),
                        entry.getAmount());
            }

            System.out.println("----------------------------------------" + "----------------------------------------");
            System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
        }
    }
}
