package library;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddMoney {
	/**
	 * @wbp.parser.entryPoint
	 */
	void add_money(String name) {
    	JFrame f1 = new JFrame("add money");
        f1.setSize(400, 249);
        f1.setLocation(200, 200);
      
        JLabel lNumber = new JLabel("amount:");
        lNumber.setBounds(67, 69, 61, 16);
        JTextField tfNumber = new JTextField("");
        tfNumber.setBounds(144, 62, 80, 30);
        tfNumber.setText("");
        tfNumber.setPreferredSize(new Dimension(80, 30));        
        JButton b = new JButton("confirm");
        b.setBounds(144, 115, 93, 29);
        f1.getContentPane().setLayout(null);

        f1.getContentPane().add(lNumber);
        f1.getContentPane().add(tfNumber);
        f1.getContentPane().add(b);
        
        b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

		        String number = tfNumber.getText();
		        Float a = new Float(number);
		        
		        boolean allRight = new TestJDBC().amend_money(name, a);
		        if(allRight) {
		        	new Gui().customer_frame(name);
		        	JFrame f2 = Judge.showOK(number+"yuan havd been added in your acount!");
		        	f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        	f2.setVisible(true);
		        }else {
		        	new Gui().customer_frame(name);
		        	JFrame f2 = Judge.showNO("Fail to add money,there must be something wrong!");
		        	f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        	f2.setVisible(true);
		        }
		        f1.dispose();
		        
			}
		});
        JButton b2 = new JButton("cancel");
        b2.setBounds(144, 156, 93, 29);
        b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f1.dispose();
				new Gui().customer_frame(name);
			}
		});
        f1.getContentPane().add(b2);
        
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f1.setVisible(true);
	}
}
