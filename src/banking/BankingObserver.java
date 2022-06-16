package banking;

import framework.*;
import framework.Holder.CompanyHolder;
import framework.Observer.IObserver;
import framework.Observer.ISubject;
import framework.ui.IMessenger;

import java.util.List;

public class BankingObserver implements IObserver<IAccount> {

    private double LIMIT_AMOUNT = 500;
    private ISubject subject;
    private IMessenger messenger;

    public BankingObserver(ISubject subject, IMessenger messenger) {
        this.subject = subject;
        this.messenger = messenger;
        subject.addObserver(this);
    }

    public void stopObserving() {
        subject.removeObserver(this);
    }

    private boolean isCompany(IAccount account) {
        return account.getHolder().getClass() == CompanyHolder.class;
    }

    private void sendEmail(IAccount object) {
    }

    public void deductUpdate(IAccount object) {
        if (isCompany(object)) {
            messenger.showMessage("Company account changed", "Withdraw was made and we send an email");
            sendEmail(object);
            return;
        }

        List<Entry> list = object.getEntryList();
        Entry last = list.get(list.size() - 1);
        if (object.getBalance() < 0 || last.getAmount() > LIMIT_AMOUNT) {
            messenger.showMessage("Personal account changed", "Withdraw was made and we send an email");
            sendEmail(object);
        }
    }

    public void increaseUpdate(IAccount object) {
        if (isCompany(object)) {
            messenger.showMessage("Company account changed", "Deposit was made and we send an email");
            sendEmail(object);
            return;
        }

        List<Entry> list = object.getEntryList();
        Entry last = list.get(list.size() - 1);
        if (object.getBalance() < 0 || last.getAmount() > LIMIT_AMOUNT) {
            messenger.showMessage("Personal account changed", "Deposit was made and we send an email");
            sendEmail(object);
        }
    }

    public void transferUpdate(IAccount object) {
        //Do nothing
    }
}
