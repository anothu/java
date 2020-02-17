package library;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class BuyBook {
	/**
	 * @wbp.parser.entryPoint
	 */
	boolean find(String username) {
		int index = 0;
    	JFrame f1 = new JFrame("search");
        f1.setSize(1000, 618);
        f1.setLocation(200, 200);
        f1.getContentPane().setLayout(null);
        
        JButton b = new JButton("buy");
        b.setBounds(807, 355, 75, 29);

        f1.getContentPane().add(b);
        String[] columnNames = new String[] { "id", "name", "author", "number","cost" };
		String[][] books = new String[15][5];
		new TestJDBC().search_to_mysql(books);
		JTable t = new JTable(books, columnNames);
		JScrollPane sp = new JScrollPane(t);
		sp.setBounds(313, 5, 454, 420);
		f1.getContentPane().add(sp);
	    f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    f1.setVisible(true);
        b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = t.getSelectedRow() ;
				String book_name = (String) t.getValueAt(index,1);
				String book_number = (String) t.getValueAt(index,3);
				String book_cost = (String)t.getValueAt(index, 4);
				Integer temp = new Integer(book_number);
				Float temp1 = new Float(book_cost);
				temp--;
				book_number = temp+"";
				String no = (String) t.getValueAt(index,0);

				boolean allRight1 = new TestJDBC().amend_to_mysql(no,book_number);
				boolean allRight2 = new TestJDBC().amend_money(username, -temp1);
				if(allRight1&&allRight2) {
					new Gui().customer_frame(username);
					Calendar calendar= Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
					new TestJDBC().insertOrder(book_name,username, book_cost,sdf.format(calendar.getTime()));
		        	JFrame f2 = Judge.showOK("You have bought a new book!");
		        	f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        	f2.setVisible(true);
		        	index = 1;
		        	
		        }else {
		        	new Gui().customer_frame(username);
		        	JFrame f2 = Judge.showNO("Fail to buy this book,there must be something wrong!");
		        	f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        	f2.setVisible(true);
		        	index = 1;
		        	
		        }
				f1.dispose();
			}
		});
        JButton b2 = new JButton("cancel");
        b2.setBounds(807, 396, 75, 29);
        b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f1.dispose();
				new Gui().customer_frame(username);
			}
		});
        f1.getContentPane().add(b2);
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f1.setVisible(true);
        
        return true;

	}
}
