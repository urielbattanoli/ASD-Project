package creditcard;

import framework.Strategy.IStrategyFactory;
import framework.ui.creditcard.CardFrm;

import javax.swing.*;

public class Application extends CardFrm {

    public Application() {
        super();

        setup();
    }

    private void setup() {
        new CreditCardObserver(getService(), this);
        getService().setGenerator(new CreditCardGenerator());
    }

    public void addPanelHook(JPanel panel) {}
    public void addListenerHook(SymAction action) {}

    public IStrategyFactory getFactory(CardType type) {
        return switch (type) {
            case GOLD -> GoldCardFactory.getInstance();
            case SILVER -> SilverCardFactory.getInstance();
            case BRONZE -> BronzeCardFactory.getInstance();
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