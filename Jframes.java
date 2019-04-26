import javax.swing.*;
import java.sql.*;

public class Jframes {
	JFrame win = new JFrame();
	JMenuBar menuBar = new JMenuBar();
	JMenu menu1 = new JMenu("Farhan");
	JMenu menu2 = new JMenu("Record");
	JMenuItem m1 = new JMenuItem("New");
	JMenuItem m2 = new JMenuItem("ABC");
	JMenuItem m3 = new JMenuItem("Enter");
	JMenuItem m4 = new JMenuItem("View");

	public Jframes() {
		menu1.add(m1);
		menu1.add(m2);
		menu2.add(m3);
		menu2.add(m4);
		menuBar.add(menu1);
		menuBar.add(menu2);
		win.setJMenuBar(menuBar);
		win.setSize(400, 400);
		win.setVisible(true);
		
	}

	public static void main(String[] args) {
		new Jframes();
	}
}
