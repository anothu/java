package library;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Judge {
	/**
	 * @wbp.parser.entryPoint
	 */

	static public JFrame showOK(String text) {
		JFrame f2 = new JFrame("");
        f2.setSize(200, 150);
        f2.setLocation(300, 300);
        f2.setLayout(new FlowLayout());
        JLabel OK = new JLabel(text);
        f2.add(OK);
        return f2;
	}
	static public JFrame showNO(String text) {
		JFrame f2 = new JFrame("");
        f2.setSize(200, 150);
        f2.setLocation(300, 300);
        f2.setLayout(new FlowLayout());
        JLabel NO = new JLabel(text);
        f2.add(NO);
        return f2;
	}
}
