package library;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class CancelPerson {
	/**
	 * @wbp.parser.entryPoint
	 */
	void add_money(String name) {
    	JFrame f1 = new JFrame("cancel");
        f1.setSize(400, 249);
        f1.setLocation(200, 200);
        f1.getContentPane().setLayout(null);
      
       
        JButton b = new JButton("confirm");
        b.setBounds(61, 165, 93, 29);        


        f1.getContentPane().add(b);
        
        JLabel lblIfYouConfirmyour = new JLabel("Your account will be deleted,click \nconfirm to go on.");
        lblIfYouConfirmyour.setBounds(22, 6, 369, 147);
        f1.getContentPane().add(lblIfYouConfirmyour);
        
        b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

		        boolean allRight = new TestJDBC().delete_Person(name);
		        if(allRight) {
		        	JFrame f2 = Judge.showOK("Googbye~ wishing for your coming back!");
		        	f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        	f2.setVisible(true);
		        	Gui.login_frame();
		        }else {
		        	JFrame f2 = Judge.showNO("Fail to cancel your account!");
		        	f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        	f2.setVisible(true);
		        }
		        f1.dispose();
		        
			}
		});
        JButton b2 = new JButton("cancel");
        b2.setBounds(238, 165, 75, 29);
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
