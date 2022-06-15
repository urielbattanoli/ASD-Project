package framework.ReportGenerator;

import framework.Account;
import framework.Holder.AccountHolder;
import framework.Entry;

import java.util.Collection;

public class ReportGenerator implements IReportGenerator {

    public String generateReport(Collection<Account> list) {
        String result = "";
        for (Account account : list) {
            AccountHolder customer = account.getHolder();
            result += "\nStatement for Account: " + account.getId();
            result += "\nAccount Holder: " + customer.getName();

            result += "\n-Date-------------------------"
                    + "-Description------------------"
                    + "-Amount-------------";

            for (Entry entry : account.getEntryList()) {
                result += String.format("\n%30s%30s%20.2f\n",
                        entry.getDate().toString(),
                        entry.getDescription(),
                        entry.getAmount());
            }

            result += "\n----------------------------------------" + "----------------------------------------";
            result += String.format("\n%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
        }
        return result;
    }
}
