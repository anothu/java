package library;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


class Amend {
	/**
	 * @wbp.parser.entryPoint
	 */
	 void change() {
    	JFrame f1 = new JFrame("amend");
        f1.setSize(500, 309);
        f1.setLocation(200, 200);
        JLabel lId = new JLabel("id：");
        lId.setBounds(138, 36, 25, 16);
        JTextField tfId = new JTextField("");
        tfId.setBounds(199, 29, 80, 30);
        tfId.setText("");
        tfId.setPreferredSize(new Dimension(80, 30));

        
        JLabel lChange = new JLabel("change number：");
        lChange.setBounds(59, 100, 110, 16);
        JTextField tfchange = new JTextField("");
        tfchange.setBounds(199, 93, 80, 30);
        tfchange.setText("");
        tfchange.setPreferredSize(new Dimension(80, 30));

        JButton b = new JButton("confirm");
        b.setBounds(186, 165, 93, 29);
        f1.getContentPane().setLayout(null);
        

        f1.getContentPane().add(lId);
        f1.getContentPane().add(tfId);
        f1.getContentPane().add(lChange);
        f1.getContentPane().add(tfchange);
        f1.getContentPane().add(b);
        
        b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		        String id = tfId.getText();
		        String change = tfchange.getText();
				boolean allRight = new TestJDBC().amend_to_mysql(id, change);
				if(allRight) {
		        	JFrame f2 = Judge.showOK("Number changed!");
		        	f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        	f2.setVisible(true);
		        }
		        else {
		        	JFrame f2 = Judge.showNO("Fail to amend,please check it out!");
		        	f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        	f2.setVisible(true);
		        }
				f1.dispose();
			}
		});
        
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f1.setVisible(true);

	}
}