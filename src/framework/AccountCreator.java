package framework;

import framework.Holder.AccountHolder;
import framework.Holder.Address;
import framework.Holder.CompanyHolder;
import framework.Holder.PersonalHolder;
import framework.Strategy.IStrategyFactory;

public class AccountCreator implements IAccountCreator {

    String accountNumber, street, city, state, zip, accountType, clientType, email, clientName, empNum, birthday;

    public Account createAccount(IStrategyFactory factory) {
        Address address = new Address(street, city, state, zip);
        AccountHolder holder;
        if (clientType == "C") {
            int number = Integer.parseInt(empNum);
            holder = new CompanyHolder(address, email, clientName, number);
        } else {
            holder = new PersonalHolder(address, email, clientName, birthday);
        }
        return new Account(accountNumber, holder, factory);
    }
}
