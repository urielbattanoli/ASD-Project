package banking;

import framework.Service;
import framework.ui.banking.BankFrm;

import javax.swing.*;

public class Application {

    static public void main(String args[])
    {
        Service service = new Service();
        BankFrm view = new BankFrm(service);
        new BankingObserver(service, view);
        try {
            // Add the following code if you want the Look and Feel
            // to be set to the Look and Feel of the native system.

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception e) {
            }

            //Create a new instance of our application's frame, and make it visible.
            view.setVisible(true);
        }
        catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }
}
