import java.sql.*;
import java.awt.*;
import java.awt.event.*;

class JavaToSQL implements ActionListener {
	TextField t1, t2, t3;
	Label l1, l2, l3;
	Button bsave;
//	Button bswitch;

	public static void main(String[] args) {
		new JavaToSQL();
//		new RecordDisplay();
	}

	JavaToSQL() {
		t1 = new TextField(20);
		t2 = new TextField(20);
		t3 = new TextField(20);
		bsave = new Button("Save");
//		bswitch = new Button("Read Records");
		l1 = new Label("Regno");
		l2 = new Label("Name");
		l3 = new Label("Marks");
		Frame f = new Frame("Database");
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		bsave.addActionListener(this);
//		bswitch.addActionListener(this);
		;

		p1.setLayout(new GridLayout(3, 2));
		p1.add(l1);
		p1.add(t1);
		p1.add(l2);
		p1.add(t2);
		p1.add(l3);
		p1.add(t3);
//		p2.add(bswitch);
		p2.add(bsave);
		f.add(p1);
		f.add(p2, BorderLayout.SOUTH);
		f.setSize(300, 200);
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
//		if (bswitch.equals((Button) event.getSource())) {
//			new RecordDisplay();
//		} else {
			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/school?allowMultipleQueries=true",
						"root", "");
				Statement st = con.createStatement();

				String query = "insert into schooltable values(" + t1.getText() + ", '" + t2.getText() + "', "
						+ t3.getText() + ")";
				// System.out.println(query);
				st.executeUpdate(query); // 24), ('5', 'hhhhh', '7'
				//
				// ResultSet rec = st.executeQuery("Select * from schooltable");
				// System.out.println("------------------------");
				// while(rec.next()){
				// System.out.println(rec.getInt(1) + ".." + rec.getString(2) + ".." +
				// rec.getInt(3));
				// }

			} catch (Exception E) {
				System.out.println(E.toString());
			}
//		}
	}

}