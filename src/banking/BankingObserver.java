package banking;

import framework.*;
import framework.Holder.CompanyHolder;
import framework.Observer.IObserver;
import framework.Observer.ISubject;
import framework.ui.IMessenger;

import java.util.List;

public class BankingObserver implements IObserver<Account> {

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

    private boolean isCompany(Account account) {
        return account.getHolder().getClass() == CompanyHolder.class;
    }

    private void sendEmail(Account object) {
    }

    public void deductDone(Account object) {
        if (isCompany(object)) {
            messenger.showMessage("Company account changed", "Withdraw was made");
            return;
        }

        List<Entry> list = object.getEntryList();
        Entry last = list.get(list.size() - 1);
        if (object.getBalance() < 0 || last.getAmount() > LIMIT_AMOUNT) {
            messenger.showMessage("Personal account changed", "Withdraw was made");
        }
        sendEmail(object);
    }

    public void increaseDone(Account object) {
        if (isCompany(object)) {
            messenger.showMessage("Company account changed", "Deposit was made");
            return;
        }

        List<Entry> list = object.getEntryList();
        Entry last = list.get(list.size() - 1);
        if (object.getBalance() < 0 || last.getAmount() > LIMIT_AMOUNT) {
            messenger.showMessage("Personal account changed", "Deposit was made");
        }
    }

    public void transferDone(Account object) {
        //Do nothing
    }
}
