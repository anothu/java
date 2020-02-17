package library;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class searchOrder {
	/**
	 * @wbp.parser.entryPoint
	 */
	void find() {
    	JFrame f1 = new JFrame("searchOrder");
        f1.setSize(1000, 618);
        f1.setLocation(200, 200);
		String[] columnNames = new String[] { "id", "bookname", "customer", "cost","time"};
		String[][] books = new String[100][5];
		double amount = new TestJDBC().searchToOrder(books);
		JTable t = new JTable(books, columnNames);
		JLabel l = new JLabel("total earning: " + amount);
		l.setBounds(333, 27, 193, 16);
		f1.getContentPane().setLayout(null);
		JScrollPane sp = new JScrollPane(t);
		sp.setBounds(333, 82, 469, 420);
		f1.getContentPane().add(sp);
		f1.getContentPane().add(l);
	    f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    f1.setVisible(true);        
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f1.setVisible(true);
	}
}
