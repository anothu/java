package library;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


class Search {
	/**
	 * @wbp.parser.entryPoint
	 */
	void find() {
    	JFrame f1 = new JFrame("search");
        f1.setSize(1000, 618);
        f1.setLocation(200, 200);
        f1.setLayout(new FlowLayout());                
		String[] columnNames = new String[] { "id", "name", "author", "number","cost"};
		String[][] books = new String[100][5];
		new TestJDBC().search_to_mysql(books);
		JTable t = new JTable(books, columnNames);
		JScrollPane sp = new JScrollPane(t);
		f1.add(sp, BorderLayout.CENTER);
	    f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    f1.setVisible(true);        
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f1.setVisible(true);
	}
}