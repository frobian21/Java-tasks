import java.awt.*;
import java.awt.event.*;

class Calculator implements ActionListener{
	TextField t1;
	Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0;
	Button badd, bminus, btimes, bdivide, bequals, btare;
	int first;
	char operation ='0';
	public static void main(String[] args){
		Calculator Fabian = new Calculator();
	}
	public Calculator(){
		Frame f = new Frame("Calculations");
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		t1 = new TextField(20);
		
		b1 = new Button("1");
		b2 = new Button("2");
		b3 = new Button("3");
		b4 = new Button("4");
		b5 = new Button("5");
		b6 = new Button("6");
		b7 = new Button("7");
		b8 = new Button("8");
		b9 = new Button("9");
		b0 = new Button("0");
		
		badd = new Button("+");
		bminus = new Button("-");
		btimes = new Button("*");
		bdivide = new Button("/");
		bequals = new Button("=");
		btare = new Button("C");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b0.addActionListener(this);
		badd.addActionListener(this);
		bminus.addActionListener(this);
		bdivide.addActionListener(this);
		bequals.addActionListener(this);
		btare.addActionListener(this);
		btimes.addActionListener(this);
	
		
		p2.setLayout(new GridLayout(4,4));
		p1.add(t1);
		p2.add(b1);
		p2.add(b2);
		p2.add(b3);
		p2.add(badd);
		p2.add(b4);
		p2.add(b5);
		p2.add(b6);
		p2.add(bminus);
		p2.add(b7);
		p2.add(b8);
		p2.add(b9);
		p2.add(btimes);
		p2.add(b0);
		p2.add(bequals);
		p2.add(btare);
		p2.add(bdivide);
		f.add(p1,BorderLayout.NORTH);
		f.add(p2);
		f.setSize(400,400);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent X){
		int x,y,r;
		x = y = r = 0;

		Button B = (Button) X.getSource();
		if(B == b1){t1.setText(t1.getText()+"1");}
		if(B == b2){t1.setText(t1.getText()+"2");}
		if(B == b3){t1.setText(t1.getText()+"3");}
		if(B == b4){t1.setText(t1.getText()+"4");}
		if(B == b5){t1.setText(t1.getText()+"5");}
		if(B == b6){t1.setText(t1.getText()+"6");}
		if(B == b7){t1.setText(t1.getText()+"7");}
		if(B == b8){t1.setText(t1.getText()+"8");}
		if(B == b9){t1.setText(t1.getText()+"9");}
		if(B == b0){t1.setText(t1.getText()+"0");}
		
		if(B == badd){
			first = Integer.parseInt(t1.getText());
			calculate();
			t1.setText("");
			operation = 'a';
		}
		if(B == bminus){
			first = Integer.parseInt(t1.getText());
			calculate();
			t1.setText("");
			operation = 'm';
		}
		if(B == bdivide){
			first = Integer.parseInt(t1.getText());
			calculate();
			t1.setText("");
			operation = 'd';
		}
		if(B == btimes){
			first = Integer.parseInt(t1.getText());
			calculate();
			t1.setText("");
			operation = 't';
		}
		if(B == bequals){
			calculate();
		}
		if(B == btare){
			t1.setText("");
		}
	}
	private void calculate(){
		switch(operation){
			case 'a': first += Integer.parseInt(t1.getText()); break;
			case 'm': first -= Integer.parseInt(t1.getText()); break;
			case 't': first *= Integer.parseInt(t1.getText()); break;
			case 'd': first /= Integer.parseInt(t1.getText()); break;
		}
		t1.setText(Integer.toString(first));
		operation = '0';
	}
}