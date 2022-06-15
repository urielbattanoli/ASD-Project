package framework.ui.banking;

import framework.*;
import framework.Holder.AccountHolder;
import framework.Holder.Address;
import framework.Holder.CompanyHolder;
import framework.Holder.PersonalHolder;
import framework.Strategy.IStrategyFactory;
import framework.ui.IMessenger;

import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

/**
 * A basic JFC based application.
 */
public abstract class BankFrm extends javax.swing.JFrame implements IMessenger {
    /****
     * init variables in the object
     ****/
    String accountnr, clientName,street,city,zip,state,clientType,amountDeposit, email, employeesNumber, birthday;
	AccountType accountType;
    boolean newaccount;
    private DefaultTableModel model;
    private JTable JTable1;
    private JScrollPane JScrollPane1;
    BankFrm myframe;
    private Object rowdata[];
	private Service service;

	public BankFrm() {
		this(new Service(new ServiceFactory()));
	}
    
	public BankFrm(Service service) {
		this.service = service;
		setupView();
	}

	public Service getService() {
		return service;
	}

	public enum AccountType { SAVING, CHECKING; }
	public void addPanelHook(JPanel panel) {}
	public void addListenerHook(SymAction action) {}
	public abstract IStrategyFactory getFactory(AccountType type);
	public void showMessage(String title, String message) {
		JOptionPane.showMessageDialog(JButton_Withdraw, message,title, JOptionPane.WARNING_MESSAGE);
	}

	private void createAccount() {
		Address address = new Address(street, city, state, zip);

		AccountHolder holder;
		IStrategyFactory factory = getFactory(accountType);
		if (clientType == "C") {
			int empNum = Integer.parseInt(employeesNumber);
			holder = new CompanyHolder(address, email, clientName, empNum);
		} else {
			holder = new PersonalHolder(address, email, clientName, birthday);
		}
		Account account = new Account(accountnr, holder, factory);
		service.saveAccount(account);
	}

	private void setupView() {
		myframe = this;
		setTitle("Bank Application.");
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
		model.addColumn("AccountNr");
		model.addColumn("Name");
		model.addColumn("City");
		model.addColumn("P/C");
		model.addColumn("Ch/S");
		model.addColumn("Amount");
		rowdata = new Object[8];
		newaccount=false;

		JPanel1.add(JScrollPane1);
		JScrollPane1.setBounds(12,92,444,160);
		JScrollPane1.getViewport().add(JTable1);
		JTable1.setBounds(0, 0, 420, 0);
//        rowdata = new Object[8];

		JButton_PerAC.setText("Add personal account");
		JPanel1.add(JButton_PerAC);
		JButton_PerAC.setBounds(24,20,192,33);
		JButton_CompAC.setText("Add company account");
		JButton_CompAC.setActionCommand("jbutton");
		JPanel1.add(JButton_CompAC);
		JButton_CompAC.setBounds(240,20,192,33);
		JButton_Deposit.setText("Deposit");
		JPanel1.add(JButton_Deposit);
		JButton_Deposit.setBounds(468,104,96,33);
		JButton_Withdraw.setText("Withdraw");
		JPanel1.add(JButton_Withdraw);
		JButton_Addinterest.setBounds(448,20,106,33);
		JButton_Addinterest.setText("Add interest");
		JPanel1.add(JButton_Addinterest);
		JButton_Withdraw.setBounds(468,164,96,33);
		JButton_Exit.setText("Exit");
		JPanel1.add(JButton_Exit);
		JButton_Exit.setBounds(468,248,96,31);
		// lineBorder1.setRoundedCorners(true);
		// lineBorder1.setLineColor(java.awt.Color.green);
		//$$ lineBorder1.move(24,312);
		addPanelHook(JPanel1);

		JButton_PerAC.setActionCommand("jbutton");

		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		SymAction lSymAction = new SymAction();
		JButton_Exit.addActionListener(lSymAction);
		JButton_PerAC.addActionListener(lSymAction);
		JButton_CompAC.addActionListener(lSymAction);
		JButton_Deposit.addActionListener(lSymAction);
		JButton_Withdraw.addActionListener(lSymAction);
		JButton_Addinterest.addActionListener(lSymAction);
		addListenerHook(lSymAction);
	}

	javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
	javax.swing.JButton JButton_PerAC = new javax.swing.JButton();
	javax.swing.JButton JButton_CompAC = new javax.swing.JButton();
	javax.swing.JButton JButton_Deposit = new javax.swing.JButton();
	javax.swing.JButton JButton_Withdraw = new javax.swing.JButton();
	javax.swing.JButton JButton_Addinterest= new javax.swing.JButton();
	javax.swing.JButton JButton_Exit = new javax.swing.JButton();

	void exitApplication() {
		try {
			this.setVisible(false);    // hide the Frame
			this.dispose();            // free the system resources
			System.exit(0);            // close the application
		} catch (Exception e) {}
	}

	class SymWindow extends java.awt.event.WindowAdapter {
		public void windowClosing(java.awt.event.WindowEvent event)
		{
			Object object = event.getSource();
			if (object == BankFrm.this)
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
		} catch (Exception e) {}
	}

	public class SymAction implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent event) {
			Object object = event.getSource();
			if (object == JButton_Exit)
				JButtonExit_actionPerformed(event);
			else if (object == JButton_PerAC)
				JButtonPerAC_actionPerformed(event);
			else if (object == JButton_CompAC)
				JButtonCompAC_actionPerformed(event);
			else if (object == JButton_Deposit)
				JButtonDeposit_actionPerformed(event);
			else if (object == JButton_Withdraw)
				JButtonWithdraw_actionPerformed(event);
			else if (object == JButton_Addinterest)
				JButtonAddinterest_actionPerformed(event);
		}
	}
    
    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    private void JButtonExit_actionPerformed(java.awt.event.ActionEvent event)
	{
		System.exit(0);
	}

	private void JButtonPerAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object 
		 set the boundaries and show it 
		*/
		
		JDialog_AddPAcc pac = new JDialog_AddPAcc(myframe);
		pac.setBounds(450, 20, 300, 330);
		pac.show();

		if (newaccount){
			createAccount();
            // add row to table
            rowdata[0] = accountnr;
            rowdata[1] = clientName;
            rowdata[2] = city;
            rowdata[3] = clientType;
            rowdata[4] = accountType;
            rowdata[5] = "0";
            model.addRow(rowdata);
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount=false;
        }
    }

	private void JButtonCompAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 construct a JDialog_AddCompAcc type object 
		 set the boundaries and 
		 show it 
		*/
		
		JDialog_AddCompAcc pac = new JDialog_AddCompAcc(myframe);
		pac.setBounds(450, 20, 300, 330);
		pac.show();
		
		if (newaccount){
			createAccount();
            // add row to table
            rowdata[0] = accountnr;
            rowdata[1] = clientName;
            rowdata[2] = city;
            rowdata[3] = clientType;
            rowdata[4] = accountType;
            rowdata[5] = "0";
            model.addRow(rowdata);
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount=false;
        }
	}

	private void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
	    // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >=0){
            String accnr = (String)model.getValueAt(selection, 0);
    	    
		    //Show the dialog for adding deposit amount for the current mane
		    JDialog_Deposit dep = new JDialog_Deposit(myframe,accnr);
		    dep.setBounds(430, 15, 275, 140);
		    dep.show();
    		
		    // compute new amount
            long deposit = Long.parseLong(amountDeposit);
			service.increaseAmount(accnr, deposit, "Deposit");
		    model.setValueAt(String.valueOf(service.getAccountBalance(accnr)), selection, 5);
		}
	}

	private void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
	    // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >=0){
            String accnr = (String)model.getValueAt(selection, 0);

		    //Show the dialog for adding withdraw amount for the current mane
		    JDialog_Withdraw wd = new JDialog_Withdraw(myframe,accnr);
		    wd.setBounds(430, 15, 275, 140);
		    wd.show();
    		
		    // compute new amount
            long deposit = Long.parseLong(amountDeposit);
			service.deductAmount(accnr, deposit, "Withdraw");
			double newamount = service.getAccountBalance(accnr);
		    model.setValueAt(String.valueOf(newamount), selection, 5);
		}
	}
	
	private void JButtonAddinterest_actionPerformed(java.awt.event.ActionEvent event) {
		service.addInterest();
		int count = model.getRowCount();
		for(int i = 0; i < count; i++) {
			String accnr = (String)model.getValueAt(i, 0);
			double amount = service.getAccountBalance(accnr);
			model.setValueAt(String.valueOf(amount), i, 5);
		}
		JOptionPane.showMessageDialog(JButton_Addinterest, "Add interest to all accounts","Add interest to all accounts",JOptionPane.WARNING_MESSAGE);
	}
}
