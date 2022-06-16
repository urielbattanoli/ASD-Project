package creditcard;

import framework.*;
import framework.Observer.IObserver;
import framework.Observer.ISubject;
import framework.ui.IMessenger;

import java.util.List;

public class CreditCardObserver implements IObserver<Account> {

    private double LIMIT_AMOUNT = 400;
    private ISubject subject;
    private IMessenger messenger;

    public CreditCardObserver(ISubject subject, IMessenger messenger) {
        this.subject = subject;
        this.messenger = messenger;
        subject.addObserver(this);
    }

    public void stopObserving() {
        subject.removeObserver(this);
    }

    private void sendEmail(Account object) {
    }

    public void deductUpdate(Account object) {
        List<Entry> list = object.getEntryList();
        Entry last = list.get(list.size() - 1);
        if (last.getAmount() > LIMIT_AMOUNT) {
            messenger.showMessage("Personal account changed", "Withdraw was made and we send an email");
            sendEmail(object);
        }
    }

    public void increaseUpdate(Account object) {
        //Do nothing
    }

    public void transferUpdate(Account object) {
        //Do nothing
    }
}