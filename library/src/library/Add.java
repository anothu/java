package library;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class Add {
	/**
	 * @wbp.parser.entryPoint
	 */
	void add_book() {
    	JFrame f1 = new JFrame("add");
        f1.setSize(500, 309);
        f1.setLocation(200, 200);
        JLabel lName = new JLabel("name：");
        lName.setBounds(109, 30, 47, 16);
        JTextField tfName = new JTextField("");
        tfName.setBounds(204, 23, 80, 30);
        tfName.setText("");
        tfName.setPreferredSize(new Dimension(80, 30));                      
        JLabel lAuthor = new JLabel("author：");
        lAuthor.setBounds(102, 69, 54, 16);
        JTextField tfAuthor = new JTextField("");
        tfAuthor.setBounds(204, 62, 80, 30);
        tfAuthor.setText("");
        tfAuthor.setPreferredSize(new Dimension(80, 30));       
        JLabel lNumber = new JLabel("number：");
        lNumber.setBounds(95, 111, 61, 16);
        JTextField tfNumber = new JTextField("");
        tfNumber.setBounds(204, 104, 80, 30);
        tfNumber.setText("");
        tfNumber.setPreferredSize(new Dimension(80, 30));  
        JLabel lCost = new JLabel("cost：");
        lCost.setBounds(116, 150, 40, 16);
        JTextField tfCost = new JTextField("");
        tfCost.setBounds(204, 143, 80, 30);
        tfCost.setText("");
        tfCost.setPreferredSize(new Dimension(80, 30));
        JButton b = new JButton("confirm");
        b.setBounds(156, 221, 93, 29);        
        f1.getContentPane().setLayout(null);
        f1.getContentPane().add(lName);
        f1.getContentPane().add(tfName);
        f1.getContentPane().add(lAuthor);
        f1.getContentPane().add(tfAuthor);
        f1.getContentPane().add(lNumber);
        f1.getContentPane().add(tfNumber);
        f1.getContentPane().add(lCost);
        f1.getContentPane().add(tfCost);
        f1.getContentPane().add(b);
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f1.setVisible(true);
        
        b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = tfName.getText();
		        String author = tfAuthor.getText();
		        String number = tfNumber.getText();
		        String cost = tfCost.getText();
		        boolean allRight = new TestJDBC().add_to_mysql(new book(name, "", author, number,cost));
		        if(allRight) {
		        	JFrame f2 = Judge.showOK("Already add a new book!");
		        	f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        	f2.setVisible(true);
		        }else {
		        	JFrame f2 = Judge.showNO("Fail to add this book,plaese check your input!");
		        	f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        	f2.setVisible(true);
		        }
		        f1.dispose();
			}
		});
        

	}
}
