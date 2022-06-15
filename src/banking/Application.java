package banking;

import framework.IStrategyFactory;
import framework.ui.banking.BankFrm;

import javax.swing.*;

public class Application extends BankFrm {

    public Application() {
        super();
        new BankingObserver(service, this);
    }

    public void addPanelHook(JPanel panel) {}
    public void addListenerHook(SymAction action) {}

    public IStrategyFactory savingFactory() {
        return SavingFactory.getInstance();
    }

    public IStrategyFactory checkingFactory() {
        return CheckingFactory.getInstance();
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
