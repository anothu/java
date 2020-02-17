package user;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import library.Judge;
import library.TestJDBC;

public class Register {
	public void add_customer() {
    	JFrame f1 = new JFrame("add");
        f1.setSize(400, 300);
        f1.setLocation(200, 200);
        f1.setLayout(new FlowLayout());
        
        JLabel lName = new JLabel("name：");
        JTextField tfName = new JTextField("");
        tfName.setText("");
        tfName.setPreferredSize(new Dimension(80, 30));
                      
        JLabel lPassword = new JLabel("password：");
        JTextField tfPassword = new JTextField("");
        tfPassword.setText("");
        tfPassword.setPreferredSize(new Dimension(80, 30));
       
        JLabel lNumber = new JLabel("identity：Customer");
       
        JButton b = new JButton("confirm");
        b.setBounds(150, 200, 100, 30);
        
        f1.add(lName);
        f1.add(tfName);

        f1.add(lPassword);
        f1.add(tfPassword);
        
        f1.add(lNumber);

        f1.add(b);
        
        b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = tfName.getText();
		        String password = tfPassword.getText();
		        String identity = "Customer";
		        boolean allRight = new TestJDBC().add_person(new Customer(name, password, identity, 0));
		        if(allRight) {
		        	JFrame f2 = Judge.showOK("Welcome to the bookstore!");
		        	f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        	f2.setVisible(true);
		        }
		        else {
		        	JFrame f2 = Judge.showNO("Error，please check it!");
		        	f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        	f2.setVisible(true);
		        }
			}
		});        
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f1.setVisible(true);
	}
}
