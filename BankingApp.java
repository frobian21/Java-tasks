import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
//import static javax.swing.GroupLayout.Alignment.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BankingApp {
	JFrame win = new JFrame();
	JMenuBar menuBar = new JMenuBar();
	JMenu menu1 = new JMenu("Bank");
	JMenuItem m1 = new JMenuItem("Open Account");
	JMenuItem m2 = new JMenuItem("Deposit Money");
	JMenuItem m3 = new JMenuItem("Withdraw Money");
	JMenuItem m4 = new JMenuItem("Check Balance");

	public BankingApp() {
		menu1.add(m1);
		menu1.add(m2);
		menu1.add(m3);
		menu1.add(m4);
		menuBar.add(menu1);
		win.setJMenuBar(menuBar);
		win.setSize(400, 400);
		win.setVisible(true);
		m1.addActionListener(new OpenAccountList());
		m2.addActionListener(new Transactions());
		m3.addActionListener(new Transactions());
		m4.addActionListener(new Transactions());
	}

	public static void main(String[] args) {
		new BankingApp();
	}
	// current_date
}

class OpenAccountList implements ActionListener {
	JTextField nameText = new JTextField(10);
	JTextField addressText = new JTextField(10);
	JRadioButton currentButton = new JRadioButton("Current");
	JRadioButton savingsButton = new JRadioButton("Savings");
	JRadioButton maleButton = new JRadioButton("Male");
	JRadioButton femaleButton = new JRadioButton("Female");

	public void actionPerformed(ActionEvent e) {
		JFrame f = new JFrame();
		JLabel nameLabel = new JLabel("Name");
		JLabel addressLabel = new JLabel("Address");
		JPanel top = new JPanel();
		JPanel mid = new JPanel();
		JPanel Account = new JPanel();
		JLabel accountLabel = new JLabel("Account Type");
		JPanel Gender = new JPanel();
		JLabel genderLabel = new JLabel("Gender");
		JButton createButton = new JButton("Create Account");
		ButtonGroup group1 = new ButtonGroup();
		ButtonGroup group2 = new ButtonGroup();
		group1.add(currentButton);
		group1.add(savingsButton);
		group2.add(maleButton);
		group2.add(femaleButton);

		f.setLayout(new GridLayout(3, 1));
		f.add(top);
		f.add(mid);
		f.add(createButton);
		top.setLayout(new GridLayout(4, 1));
		top.add(nameLabel);
		top.add(nameText);
		top.add(addressLabel);
		top.add(addressText);
		mid.setLayout(new GridLayout(1, 2));
		mid.add(Account);
		mid.add(Gender);
		Account.setLayout(new GridLayout(3, 1));
		Account.add(accountLabel);
		Account.add(currentButton);
		Account.add(savingsButton);
		Gender.setLayout(new GridLayout(3, 1));
		Gender.add(genderLabel);
		Gender.add(maleButton);
		Gender.add(femaleButton);

		createButton.addActionListener(new ActionListener() {
			JFrame f = new JFrame();

			public void actionPerformed(ActionEvent e) {
				String acctype = "";
				String gen = "";
				String errormessage = "<html><body>";
				String myString = "000";
				if (nameText.getText().equals(""))
					errormessage += "Name cannot be empty<br>";
				if (addressText.getText().equals(""))
					errormessage += "Address cannot be empty<br>";
				if (currentButton.isSelected())
					acctype = "c";
				else if (savingsButton.isSelected())
					acctype = "s";
				else
					errormessage += "You must choose account type<br>";
				if (maleButton.isSelected())
					gen = "m";
				else if (femaleButton.isSelected())
					gen = "f";
				else
					errormessage += "You must choose gender<br>";
				errormessage += "</body></html>";
				if (!errormessage.equals("<html><body></body></html>")) {
					f.add(new JLabel(errormessage));
					f.setSize(150, 150);
					f.setVisible(true);
				}

				if (errormessage.equals("<html><body></body></html>")) {
					try {
						Connection con = DriverManager.getConnection(
//							"jdbc:mysql://localhost/banking?allowMultipleQueries=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false",
								"jdbc:mysql://localhost/banking?serverTimezone=BST", "root", "");
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery(
								"select lpad(maxed,3,0) from (select max(substr(accno,3,3))+1 As MAXED from bank where substr(accno,1,1)= '"
										+ acctype + "') as T");
						if (rs.next()) {
							myString = rs.getString(1);
							System.out.println(rs.getString(1));
						}
						st.executeUpdate("insert into bank values (concat('" + acctype + gen + myString + "'),'"
								+ nameText.getText() + "','" + addressText.getText() + "')");

						f.add(new Label("Account number: " + acctype + gen + myString));
						f.setSize(150, 150);
						f.setVisible(true);

					} catch (Exception E) {
						System.out.println(E.toString());
					}
					nameText.setText("");
					addressText.setText("");
				}
			}
		});

		f.setSize(400, 400);
		f.setVisible(true);

	}
}

class Transactions extends KeyAdapter implements ActionListener{
	JFrame transactions = new JFrame();
	JLabel accLabel = new JLabel("Accno");
	JTextField accNo = new JTextField(5);
	JButton checkAcc = new JButton("...");
	JLabel nameLabel = new JLabel("Name");
	JLabel name = new JLabel();
	JLabel addressLabel = new JLabel("Address");
	JLabel address = new JLabel();
	JLabel accountLabel = new JLabel("Account");
	JLabel account = new JLabel();
	String transac = "";
	JLabel transacLabel = new JLabel(transac);
	JTextField amount = new JTextField();
	JLabel balanceLabel = new JLabel("Current Balance");
	JLabel balance = new JLabel();
	JLabel newbalanceLabel = new JLabel("New Balance");
	JLabel newbalance = new JLabel();
	JButton save = new JButton("Save");
	int bal, val;

	public void actionPerformed(ActionEvent e) {
		bal = 0;
		val = 0;
		GroupLayout layout = new GroupLayout(transactions.getContentPane());
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		GroupLayout.ParallelGroup h1Group = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		GroupLayout.ParallelGroup h2Group = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		GroupLayout.ParallelGroup h3Group = layout.createParallelGroup(GroupLayout.Alignment.LEADING);

		transactions.getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setVerticalGroup(vGroup);
		layout.setHorizontalGroup(hGroup);
		vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(accLabel)
				.addComponent(accNo).addComponent(checkAcc));
		hGroup.addGroup(h1Group).addGroup(h2Group).addGroup(h3Group);
		h1Group.addComponent(accLabel);
		h2Group.addComponent(accNo);
		h3Group.addComponent(checkAcc);

		transactions.setSize(400, 100);
		transactions.setVisible(true);
		checkAcc.addActionListener(this);
		save.addActionListener(this);
		amount.addKeyListener(this);
		if (((Object) e.getSource()).equals(checkAcc) || ((Object) e.getSource()).equals(save)) {
			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/banking?serverTimezone=BST",
						"root", "");
				Statement st = con.createStatement();
				ResultSet rs = st
						.executeQuery("select name, address from bank where accno = '" + accNo.getText() + "'");
				if (rs.next()) {
					h1Group.addComponent(nameLabel).addComponent(addressLabel).addComponent(accountLabel)
							.addComponent(transacLabel).addComponent(newbalanceLabel);
					h2Group.addComponent(name).addComponent(address).addComponent(account).addComponent(amount)
							.addComponent(balanceLabel).addComponent(newbalance);
					h3Group.addComponent(balance).addComponent(save);

					vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(nameLabel)
							.addComponent(name))
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
									.addComponent(addressLabel).addComponent(address))
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
									.addComponent(accountLabel).addComponent(account))
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
									.addComponent(transacLabel).addComponent(amount))
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
									.addComponent(balanceLabel).addComponent(balance))
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
									.addComponent(newbalanceLabel).addComponent(newbalance).addComponent(save));

					name.setText(rs.getString(1));
					address.setText(rs.getString(2));
					if (accNo.getText().substring(0, 1).equals("c")) {
						account.setText("Current");
					} else if (accNo.getText().substring(0, 1).equals("s")) {
						account.setText("Saving");
					} else
						System.out.println("Error with account type comparison");
					transacLabel.setText(transac);
					rs = st.executeQuery("select sum(amount) from deposit where accno = '" + accNo.getText() + "'");
					if (rs.next())
						bal = rs.getInt(1);
					else
						bal = 0;
					rs = st.executeQuery("select sum(amount) from withdraw where accno = '" + accNo.getText() + "'");
					if (rs.next())
						bal -= rs.getInt(1);
					else
						bal -= 0;
					balance.setText(Integer.toString(bal));
					transactions.setSize(400, 300);
					if (((JButton) e.getSource()).equals(save) && !amount.getText().equals("")) {
						if (transac.equals("Withdraw") || transac.equals("Deposit")) {
							st.executeUpdate("insert into " + transac + " values ('" + accNo.getText() + "', " + val
									+ ", current_date)");
							amount.setText("");
						}
					}
				} else
					System.out.println("Account number not found");
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Something went wrong");
			}
		} else {
			if (((JMenuItem) e.getSource()).getText().equals("Deposit Money")) {
				transac = "Deposit";
			} else if (((JMenuItem) e.getSource()).getText().equals("Withdraw Money")) {
				transac = "Withdraw";
			} else if (((JMenuItem) e.getSource()).getText().equals("Check Balance")) {
				transac = "Check Balance";
			} else
				System.out.println("Error with String comparison");

		}
//		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
//		hGroup.addGroup(layout.createParallelGroup().addComponent(nameLabel).addComponent(addressLabel));
//		hGroup.addGroup(layout.createParallelGroup().addComponent(nameText).addComponent(addressText)); //cant get grouplayout to work

	}
    public void keyReleased(KeyEvent e) {
        JTextField textField = (JTextField) e.getSource();
        try{
        	if(textField.getText().equals(""))
        		val = 0;
        	else
        		val = Integer.parseInt(textField.getText());
			if (transac.equals("Withdraw")) {
				if (bal - Integer.parseInt(textField.getText()) < 0)
					System.out.println("Insufficient funds");
				else
					newbalance.setText(Integer.toString(bal - val));
			} else
				newbalance.setText(Integer.toString(bal + val));
	    }
        catch
//        (NumberFormatException E) {
//			System.out.println("Only numbers please");
//			newbalance.setText("error");
//        }
        (Exception E) {
			System.out.println(E.toString());
			newbalance.setText("error");
        }
    }

}
