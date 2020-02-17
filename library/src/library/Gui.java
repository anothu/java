package library;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import user.Customer;
import user.Register;
public class Gui {
	public static void main(String[] args) {
		login_frame();
	}
	public static void fail_frame() {
		JFrame fail_frame = new JFrame("Fail");
		JLabel fail = new JLabel("             No match!Please input again!");
        fail_frame.setSize(300, 100);
        fail_frame.setLocation(250, 250);
        fail_frame.getContentPane().add(fail);
        fail_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fail_frame.setVisible(true);
	}
	public static void login_frame() {
		//frame
		/**
		 * @wbp.parser.entryPoint
		 */
    	JFrame f1 = new JFrame("login");    	
        //插入图片
        ImageIcon icon1=new ImageIcon("src/bg.jpg" );
        //添加JLabel 放置图片
        JLabel label1=new JLabel(icon1);
        //设置label的位置、大小，label大小为图片的大小
        label1.setBounds(0,0,icon1.getIconWidth(),icon1.getIconHeight());
    	
        f1.setSize(500, 309);
        f1.setLocation(200, 200);
		JLabel lName = new JLabel("name：");
		lName.setBounds(154, 58, 47, 16);
		lName.setForeground(Color.WHITE);
	    JTextField tfName = new JTextField("");
	    tfName.setBounds(213, 51, 80, 30);
	    tfName.setText("");
	    tfName.setPreferredSize(new Dimension(80, 30));	                	      
	    JLabel lPassword = new JLabel("password：");
	    lPassword.setBounds(128, 104, 73, 16);
	    lPassword.setForeground(Color.white);
	    JPasswordField tfPassword = new JPasswordField("");
	    tfPassword.setBounds(213, 97, 80, 30);
	    tfPassword.setText("");
	    tfPassword.setPreferredSize(new Dimension(80, 30));	        
	    f1.getContentPane().setLayout(null);
	    
	    f1.getContentPane().add(lName);
	    f1.getContentPane().add(tfName);
	    f1.getContentPane().add(lPassword);
	    f1.getContentPane().add(tfPassword);
	    
        JButton b = new JButton("confirm");
        b.setBounds(154, 150, 93, 29);
        f1.getContentPane().add(b);
        JButton b1 = new JButton("register");
        b1.setBounds(285, 150, 92, 29);
        f1.getContentPane().add(b1);
        f1.getContentPane().add(label1);
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f1.setVisible(true);
        //button
        b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			    String name = tfName.getText();
		        String password = tfPassword.getText();
				if(new TestJDBC().is_Admin(name, password)) {					
		        	admin_frame();
		        	f1.dispose();
		        }else if(new TestJDBC().is_Customer(name, password)) {
		        	customer_frame(name);
		        	f1.dispose();
		        }
				else {
					fail_frame();
				}
			}
		});
        b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			   new Register().add_customer();
			}
		});
        
	}
	public static void register_frame() {
		JFrame f1 = new JFrame("register");
        f1.setSize(1000, 618);
        f1.setLocation(200, 200);
        f1.getContentPane().setLayout(new FlowLayout());
		JLabel lName = new JLabel("name：");
	    JTextField tfName = new JTextField("");
	    tfName.setText("");
	    tfName.setPreferredSize(new Dimension(80, 30));	                	      
	    JLabel lPassword = new JLabel("password：");
	    JTextField tfPassword = new JTextField("");
	    tfPassword.setText("");
	    tfPassword.setPreferredSize(new Dimension(80, 30));	        
	    f1.getContentPane().add(lName);
	    f1.getContentPane().add(tfName);
	    f1.getContentPane().add(lPassword);
	    f1.getContentPane().add(tfPassword);	    
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f1.setVisible(true);
	    String name = tfName.getText();
        String password = tfPassword.getText();
        JButton b = new JButton("confirm");
        b.setBounds(500, 50, 100, 30);
        f1.getContentPane().add(b);
        //button
        b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TestJDBC().add_person(new Customer(name, password, "Customer", 0));
			}
		});
	}
	public static void customer_frame(String username) {
		JFrame f = new JFrame("网上书店");
		f.setSize(750, 464);
        f.setLocation(100, 62);
        f.getContentPane().setLayout(null);        
        JButton b1 = new JButton("购买图书");
        b1.setBounds(500, 39, 100, 29);
        JButton b2 = new JButton("充值");
        b2.setBounds(500, 91, 100, 29);
        JButton b3 = new JButton("注销用户");
        b3.setBounds(500, 150, 100, 29);
        JButton b4 = new JButton("退出");
        b4.setBounds(500, 210, 100, 29);
        
		String[] columnNames = new String[] { "id", "name", "author", "number","cost"};
		String[][] books = new String[100][5];
		new TestJDBC().search_to_mysql(books);
		JTable t = new JTable(books, columnNames);
		JScrollPane sp = new JScrollPane(t);
		sp.setBounds(90, 43, 369, 361);
        
        String money = new TestJDBC().show_money(username);
        JLabel label = new JLabel("your money: ");
        
        label.setBounds(515,248,100,100);
        JLabel last_money = new JLabel(money);
        last_money.setBounds(515,304,100,100);
        
       
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new BuyBook().find(username);  
            	f.dispose();
            }
        });
        
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new AddMoney().add_money(username);
            	f.dispose();
            }
        });
        
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new CancelPerson().add_money(username);
            	f.dispose();            	
            }
        });
        
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	login_frame();
            	f.dispose();
            }
        });
        

        
       
        f.getContentPane().add(b1);
        f.getContentPane().add(b2);
        f.getContentPane().add(b3);
        f.getContentPane().add(b4);
        f.getContentPane().add(last_money);
        f.getContentPane().add(label);
        f.getContentPane().add(sp);
        
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
	
	}
	public static void admin_frame() {
		JFrame f = new JFrame("网上书店");
		f.setSize(800, 618);
        f.setLocation(100, 62);
        
		String[] columnNames = new String[] { "id", "name", "author", "number","cost"};
		String[][] books = new String[100][5];
		new TestJDBC().search_to_mysql(books);
		JTable t = new JTable(books, columnNames);
		JScrollPane sp = new JScrollPane(t);
		sp.setBounds(90, 43, 469, 461);
        
        JButton b1 = new JButton("增加图书");
        b1.setBounds(626, 41, 100, 24);
        JButton b2 = new JButton("删除图书");
        b2.setBounds(626, 94, 100, 24);
        JButton b3 = new JButton("修改数量");
        b3.setBounds(626, 149, 100, 24);
        //JButton b4 = new JButton("查询图书");
        //b4.setBounds(859, 221, 100, 24);
        JButton b5 = new JButton("退出");
        b5.setBounds(626, 267, 100, 24);
        JButton b6 = new JButton("统计");
        b6.setBounds(626, 203, 100, 24);
        
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new Add().add_book();
            }
        });
        
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new Delete().Remove();
            }
        });
        
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new Amend().change();
            }
        });
        
        /*b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new Search().find();          
            }
        });
        */
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	login_frame();
            	f.dispose();
            }
            
        });
        
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new searchOrder().find();
            }
            
        });
        f.getContentPane().setLayout(null);
        
       
        f.getContentPane().add(b1);
        f.getContentPane().add(b2);
        f.getContentPane().add(b3);
        //f.getContentPane().add(b4);
        f.getContentPane().add(b5);
        f.getContentPane().add(b6);
        f.getContentPane().add(sp);
        
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
	}

}
