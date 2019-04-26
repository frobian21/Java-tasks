import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class DataMenu {
	DBF2 updateRecord = new DBF2();
	DBF viewRecord = new DBF();
	Frame fmenu = new Frame();

	public static void main(String[] args) {
		new DataMenu();
	}

	public DataMenu() {
		fmenu.setLayout(new GridLayout(2, 1));
		Button bEntry = new Button("Data Entry");
		Button bView = new Button("Data View");
		fmenu.add(bEntry);
		fmenu.add(bView);

		WindowEvents wevent = new WindowEvents(fmenu);
		fmenu.addWindowListener(wevent);
		
		
		fmenu.setSize(200, 200);
		fmenu.setVisible(true);
		
		
		bEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent E) {
				updateRecord.f.setVisible(true);
			}
		});
		bView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent E) {
				viewRecord.f.setVisible(true);
			}
		});

	}
}
class WindowEvents implements WindowListener{
	Frame menu;
	public WindowEvents() {}
	public WindowEvents(Frame f){
		menu = f;
	}
	public void windowOpened(WindowEvent e) {
		System.out.println("window opened");
	}
	public void windowClosing(WindowEvent e) {
		System.out.println("window closing");
		Frame temp = (Frame) e.getSource();
//		try {
//		if( menu.equals(temp)) {
//			System.exit(0);
//		}
//		}catch(Exception E) {
			temp.dispose();
//		}
//		
	}
	public void windowClosed(WindowEvent e) {
		System.out.println("window closed");
	}
	public void windowIconified(WindowEvent e) {
		System.out.println("window iconified");
	}
	public void windowDeiconified(WindowEvent e) {
		System.out.println("window deiconified");
	}
	public void windowActivated(WindowEvent e) {
		System.out.println("window activated");
	}
	public void windowDeactivated(WindowEvent e) {
		System.out.println("window deactivated");
	}
}

class DBF2 implements ActionListener {
	TextField t2, t3;
	Label l2, l3;
	Button bsave;
	Frame f;
	Panel p1, p2;

	DBF2() {
		t2 = new TextField(20);
		t3 = new TextField(20);
		bsave = new Button("Save");
		l2 = new Label("Name");
		l3 = new Label("Marks");
		f = new Frame("Database");
		p1 = new Panel();
		p2 = new Panel();
		bsave.addActionListener(this);
		WindowEvents wevent = new WindowEvents();
		f.addWindowListener(wevent);

		p1.setLayout(new GridLayout(3, 2));
		p1.add(l2);
		p1.add(t2);
		p1.add(l3);
		p1.add(t3);

		p2.add(bsave);
		f.add(p1);
		f.add(p2, BorderLayout.SOUTH);
		f.setSize(300, 200);
	}

	public void actionPerformed(ActionEvent event) {

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/QA",
					"root", "");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select max(regno)+1 as r from school");
			rs.next();
			String query = "insert into school values(" + rs.getInt(1) + ", '" + t2.getText() + "', "
					+ t3.getText() + ")";
			st.executeUpdate(query);
			
		} catch (Exception E) {
			System.out.println(E.toString());
		}

	}
}

class DBF {
	Frame f;
	TextField t1, t2, t3, t4, t5;
	Label l1, l2, l3, l4, l5;
	ResultSet rs;
	Button b1;

	public DBF() {
		t1 = new TextField(10);
		t2 = new TextField(10);
		t3 = new TextField(10);
		t4 = new TextField(10);
		t5 = new TextField(10);
		l1 = new Label("Regno");
		l2 = new Label("Name");
		l3 = new Label("Marks");
		l4 = new Label("Percentage");
		l5 = new Label("Result");
		b1 = new Button("Next");
		f = new Frame("Read Records");
		WindowEvents wevent = new WindowEvents();
		f.addWindowListener(wevent);
		f.setLayout(new GridLayout(6, 2));
		f.add(l1);
		f.add(t1);
		f.add(l2);
		f.add(t2);
		f.add(l3);
		f.add(t3);
		f.add(l4);
		f.add(t4);
		f.add(l5);
		f.add(t5);
		f.add(new Label());
		f.add(b1);
		f.setSize(400, 400);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent X) {
				try {
					if (rs.next())
						ShowRecord();
					else
						System.out.println("No more records");
				} catch (Exception E) {
					System.out.println(E.toString());
				}
			}
		});
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/QA",
					"root", "");
			Statement st = con.createStatement();
			rs = st.executeQuery("select * from school");
			if (rs.next())
				ShowRecord();
			else
				System.out.println("No records");

		} catch (Exception E) {
			System.out.println(E.toString());
		}
	}

	public void ShowRecord() {
		int regno, marks;
		float per;
		String name, result;
		try {
			regno = rs.getInt(1);
			marks = rs.getInt(3);
			name = rs.getString(2);
			per = (float) marks * 100 / 150;
			if (per >= 60)
				result = "Pass";
			else
				result = "Fail";
			t1.setText(Integer.toString(regno));
			t2.setText(name);
			t3.setText(Integer.toString(marks));
			t4.setText(Float.toString(per));
			t5.setText(result);
		} catch (Exception E) {
			System.out.println(E.toString());
		}
	}

}