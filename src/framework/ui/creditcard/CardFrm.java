package framework.ui.creditcard;

import framework.*;
import framework.Holder.AccountHolder;
import framework.Holder.Address;
import framework.Holder.PersonalHolder;
import framework.ReportGenerator.IReportGenerator;
import framework.Strategy.IStrategyFactory;
import framework.ui.IMessenger;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * A basic JFC based application.
 */


public abstract class CardFrm extends javax.swing.JFrame implements IMessenger {
    /****
     * init variables in the object
     ****/
    String clientName,street,city, zip, state,amountDeposit,expdate, ccnumber, email;
	CardType accountType;
    boolean newaccount;
    private DefaultTableModel model;
    private JTable JTable1;
    private JScrollPane JScrollPane1;
    CardFrm thisframe;
    private Object rowdata[];
	private Service service;
	protected IReportGenerator generator;

	public CardFrm() {
		this(new Service(new ServiceFactory()));
	}

	public CardFrm(Service service) {
		this.service = service;
		setupView();
	}

	public Service getService() {
		return service;
	}

	public enum CardType { GOLD, SILVER, BRONZE; }
	public void addPanelHook(JPanel panel) {}
	public void addListenerHook(SymAction action) {}
	public abstract IStrategyFactory getFactory(CardType type);
	public void showMessage(String title, String message) {
		JOptionPane.showMessageDialog(JButton_Withdraw, message,title, JOptionPane.WARNING_MESSAGE);
	}

	public void createAccount() {
		Address address = new Address(street, city, state, zip);
		AccountHolder holder = new PersonalHolder(address, email, clientName, "");
		IStrategyFactory factory = getFactory(accountType);
		Account account = new CreditAccount(ccnumber, holder, factory, expdate);
		service.saveAccount(account);
	}
    
	private void setupView() {
		thisframe=this;
		
		setTitle("Credit-card processing Application.");
		setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0,0));
		setSize(575,310);
		setVisible(false);
		JPanel1.setLayout(null);
		getContentPane().add(BorderLayout.CENTER, JPanel1);
		JPanel1.setBounds(0,0,575,310);
		/*
		/Add five buttons on the pane 
		/for Adding personal account, Adding company account
		/Deposit, Withdraw and Exit from the system
		*/
        JScrollPane1 = new JScrollPane();
        model = new DefaultTableModel();
        JTable1 = new JTable(model);
        model.addColumn("Name");
        model.addColumn("CC number");
        model.addColumn("Exp date");
        model.addColumn("Type");
        model.addColumn("Balance");
        rowdata = new Object[7];
        newaccount=false;
        
        
        JPanel1.add(JScrollPane1);
        JScrollPane1.setBounds(12,92,444,160);
        JScrollPane1.getViewport().add(JTable1);
        JTable1.setBounds(0, 0, 420, 0);
//        rowdata = new Object[8];
		
		JButton_NewCCAccount.setText("Add Credit-card account");
		JPanel1.add(JButton_NewCCAccount);
		JButton_NewCCAccount.setBounds(24,20,192,33);
		JButton_GenBill.setText("Generate Monthly bills");
		JButton_GenBill.setActionCommand("jbutton");
		JPanel1.add(JButton_GenBill);
		JButton_GenBill.setBounds(240,20,192,33);
		JButton_Deposit.setText("Deposit");
		JPanel1.add(JButton_Deposit);
		JButton_Deposit.setBounds(468,104,96,33);
		JButton_Withdraw.setText("Charge");
		JPanel1.add(JButton_Withdraw);
		JButton_Withdraw.setBounds(468,164,96,33);
		JButton_Exit.setText("Exit");
		JPanel1.add(JButton_Exit);
		JButton_Exit.setBounds(468,248,96,31);
		addPanelHook(JPanel1);


		JButton_GenBill.setActionCommand("jbutton");

		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		SymAction lSymAction = new SymAction();
		JButton_Exit.addActionListener(lSymAction);
		JButton_NewCCAccount.addActionListener(lSymAction);
		JButton_GenBill.addActionListener(lSymAction);
		JButton_Deposit.addActionListener(lSymAction);
		JButton_Withdraw.addActionListener(lSymAction);
		addListenerHook(lSymAction);
	}

	javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
	javax.swing.JButton JButton_NewCCAccount = new javax.swing.JButton();
	javax.swing.JButton JButton_GenBill = new javax.swing.JButton();
	javax.swing.JButton JButton_Deposit = new javax.swing.JButton();
	javax.swing.JButton JButton_Withdraw = new javax.swing.JButton();
	javax.swing.JButton JButton_Exit = new javax.swing.JButton();


	private void exitApplication() {
		try {
		    	this.setVisible(false);    // hide the Frame
		    	this.dispose();            // free the system resources
		    	System.exit(0);            // close the application
		} catch (Exception e) {
		}
	}

	class SymWindow extends java.awt.event.WindowAdapter {
		public void windowClosing(java.awt.event.WindowEvent event)
		{
			Object object = event.getSource();
			if (object == CardFrm.this)
				BankFrm_windowClosing(event);
		}
	}

	private void BankFrm_windowClosing(java.awt.event.WindowEvent event) {
		// to do: code goes here.
			 
		BankFrm_windowClosing_Interaction1(event);
	}

	private void BankFrm_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
		try {
			this.exitApplication();
		} catch (Exception e) {
		}
	}

	public class SymAction implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == JButton_Exit)
				JButtonExit_actionPerformed(event);
			else if (object == JButton_NewCCAccount)
				JButtonNewCCAC_actionPerformed(event);
			else if (object == JButton_GenBill)
				JButtonGenerateBill_actionPerformed(event);
			else if (object == JButton_Deposit)
				JButtonDeposit_actionPerformed(event);
			else if (object == JButton_Withdraw)
				JButtonWithdraw_actionPerformed(event);
			
		}
	}
    
    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    private void JButtonExit_actionPerformed(java.awt.event.ActionEvent event)
	{
		System.exit(0);
	}

	private void JButtonNewCCAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object 
		 set the boundaries and show it 
		*/
		
		JDialog_AddCCAccount ccac = new JDialog_AddCCAccount(thisframe);
		ccac.setBounds(450, 20, 300, 380);
		ccac.show();

		if (newaccount){
			createAccount();
            // add row to table
            rowdata[0] = clientName;
            rowdata[1] = ccnumber;
            rowdata[2] = expdate;
            rowdata[3] = accountType;
            rowdata[4] = "0";
            model.addRow(rowdata);
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount=false;
        }
    }

	private void JButtonGenerateBill_actionPerformed(java.awt.event.ActionEvent event) {
		JDialogGenBill billFrm = new JDialogGenBill(service.generateReport());
		billFrm.setBounds(450, 20, 400, 350);
		billFrm.show();
	}

	private void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
	    // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >=0){
			String name = (String)model.getValueAt(selection, 0);
    	    
		    //Show the dialog for adding deposit amount for the current mane
		    JDialog_Deposit dep = new JDialog_Deposit(thisframe,name);
		    dep.setBounds(430, 15, 275, 140);
		    dep.show();
    		
		    // compute new amount
			String ccNumber = (String)model.getValueAt(selection, 1);
			long deposit = Long.parseLong(amountDeposit);
			service.increaseAmount(ccNumber, deposit, "Deposit");
			model.setValueAt(String.valueOf(service.getAccountBalance(ccNumber)), selection, 4);
		}
	}

	private void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
	    // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >=0){
            String name = (String)model.getValueAt(selection, 0);

		    //Show the dialog for adding withdraw amount for the current mane
		    JDialog_Withdraw wd = new JDialog_Withdraw(thisframe,name);
		    wd.setBounds(430, 15, 275, 140);
		    wd.show();
    		
		    // compute new amount
			long deposit = Long.parseLong(amountDeposit);
			service.deductAmount(ccnumber, deposit, "Charge");
			double newamount = service.getAccountBalance(ccnumber);
			model.setValueAt(String.valueOf(newamount), selection, 4);
		}
	}
}
