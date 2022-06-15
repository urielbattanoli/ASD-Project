package banking;

import framework.Account;
import framework.Holder.AccountHolder;
import framework.Holder.Address;
import framework.Holder.CompanyHolder;
import framework.Holder.PersonalHolder;
import framework.Strategy.IStrategyFactory;
import framework.ui.banking.BankFrm;

import javax.swing.*;

public class Application extends BankFrm {

    public Application() {
        super();
        new BankingObserver(getService(), this);
    }

    public void addPanelHook(JPanel panel) {}
    public void addListenerHook(SymAction action) {}

    public IStrategyFactory getFactory(AccountType type) {
        return switch (type) {
            case SAVING -> SavingFactory.getInstance();
            case CHECKING -> CheckingFactory.getInstance();
        };
    }

    static public void main(String args[]) {
        try {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception e) {}
            (new Application()).setVisible(true);
        }
        catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
}
