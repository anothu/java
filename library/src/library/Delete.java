package library;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class Delete {
	/**
	 * @wbp.parser.entryPoint
	 */
	void Remove() {
    	JFrame f1 = new JFrame("delete");
        f1.setSize(400, 248);
        f1.setLocation(200, 200);
        
        JLabel lId = new JLabel("id：");
        lId.setBounds(98, 52, 25, 16);
        JTextField tfId = new JTextField("");
        tfId.setBounds(139, 45, 80, 30);
        tfId.setText("");
        tfId.setPreferredSize(new Dimension(80, 30));
         
        JButton b = new JButton("confirm");
        b.setBounds(139, 178, 93, 29);
        f1.getContentPane().setLayout(null);
        
        f1.getContentPane().add(lId);
        f1.getContentPane().add(tfId);
        f1.getContentPane().add(b);
        
        b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		        //String name = tfName.getText();
		        String id = tfId.getText();
				boolean allRight = new TestJDBC().delete_to_mysql(id);
				if(allRight) {
		        	JFrame f2 = Judge.showOK("Delete completely!");
		        	f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        	f2.setVisible(true);
		        }
		        else {
		        	JFrame f2 = Judge.showNO("Fail to delete this book!");
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
