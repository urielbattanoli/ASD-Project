package creditcard;

import framework.*;
import framework.Observer.IObserver;
import framework.Observer.ISubject;
import framework.ui.IMessenger;

import java.util.List;

public class CreditCardObserver implements IObserver<Account> {

    private double LIMIT_AMOUNT = 500;
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

    public void deductDone(Account object) {
        List<Entry> list = object.getEntryList();
        Entry last = list.get(list.size() - 1);
        if (object.getBalance() < 0 || last.getAmount() > LIMIT_AMOUNT) {
            messenger.showMessage("Personal account changed", "Withdraw was made");
        }
        sendEmail(object);
    }

    public void increaseDone(Account object) {
        //Do nothing
    }

    public void transferDone(Account object) {
        //Do nothing
    }
}