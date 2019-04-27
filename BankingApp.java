import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;

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

//		GroupLayout layout = new GroupLayout(top);
//		top.setLayout(layout);
//		layout.setAutoCreateGaps(true);
//		layout.setAutoCreateContainerGaps(true);
//		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
//		hGroup.addGroup(layout.createParallelGroup().addComponent(nameLabel).addComponent(addressLabel));
//		hGroup.addGroup(layout.createParallelGroup().addComponent(nameText).addComponent(addressText)); //cant get grouplayout to work

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

						f.add(new Label("Account number: "+acctype + gen + myString));
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
