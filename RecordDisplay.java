import java.sql.*;
import java.awt.*;
import java.awt.event.*;

class RecordDisplay {
	Frame f;
	TextField t1, t2, t3, t4, t5;
	Label l1, l2, l3, l4, l5;
	ResultSet rs;
	Button b1;

	public RecordDisplay() {
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
		f.setVisible(true);
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
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/school?allowMultipleQueries=true",
					"root", "");
			Statement st = con.createStatement();
			rs = st.executeQuery("select * from schooltable");
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
