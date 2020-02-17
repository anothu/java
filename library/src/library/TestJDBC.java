package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import user.*;
  
public class TestJDBC {
	static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "Library";
    static String encoding = "UTF-8";
    static String loginName = "root";
    static String password = "mysql0123xylxyy";
    
    boolean add_to_mysql(book library) {
    	String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  
        try (
            Connection c = DriverManager.getConnection(url,loginName,password);//连接数据库
            Statement s = c.createStatement(); //用于执行sql语句             
        )//try with resource
        {        	
        		String sql = String.format("insert into WUHAN values(NULL,'%s','%s','%s','%s');"
        				,library.book_number,library.book_name,library.book_author,library.book_cost);
                s.execute(sql);             
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    boolean delete_to_mysql(String id) {
    	String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
  
        try (
            Connection c = DriverManager.getConnection(url,loginName,password);//连接数据库
            Statement s = c.createStatement(); //用于执行sql语句             
        )//try with resource
        {        	
        		String sql = String.format("delete from WUHAN where id = %s;",id);
                s.execute(sql);             
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public  boolean amend_to_mysql(String id,String change) {
    	String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
  
        try (
            Connection c = DriverManager.getConnection(url,loginName,password);//连接数据库
            Statement s = c.createStatement(); //用于执行sql语句             
        )//try with resource
        {        	
        		String sql = String.format("update WUHAN set number = %s where id = %s;",change,id);
                s.execute(sql);             
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    boolean search_to_mysql(String[][] books) {
    	String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
  
        try (
            Connection c = DriverManager.getConnection(url,loginName,password);//连接数据库
            Statement s = c.createStatement(); //用于执行sql语句             
        )//try with resource
        {       int index = 0; 	
        		String sql = String.format("select * from WUHAN;");
        		ResultSet rs = s.executeQuery(sql);  
        		while (rs.next()) {
                    String id = rs.getString("id");// 可以使用字段名
                    String name = rs.getString("name");
                    String number  = rs.getString("number");
                    String author = rs.getString("author");
                    String cost = rs.getString("cost"); 					 
   					books[index][0] = id;
   					books[index][1] = name;
   					books[index][2] = author;
   					books[index][3] = number;
   					books[index][4] = cost;
   					index++;
   				}	 
   		} catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public  boolean reduce_money(String username,float change) {
    	String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
  
        try (
            Connection c = DriverManager.getConnection(url,loginName,password);//连接数据库
            Statement s = c.createStatement(); //用于执行sql语句             
        )//try with resource
        {       String sql = String.format("select * from USERS where name='%s';",username);
				ResultSet rs = s.executeQuery(sql);
				String money = rs.getString("money");
				Float money0 = new Float(money);
				if((money0-change)>=0) {
					String moneyAfter = (money0-change)+""; 
					String sql1 = String.format("update USERS set money = %s where name = %s;",moneyAfter,username);
	                s.execute(sql1);
	                return true;
				}
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean add_person(Customer p) {
    	String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	try (
    			Connection c = DriverManager.getConnection(url,loginName,password);//连接数据库
    			Statement s = c.createStatement(); //用于执行sql语句             
    			)//try with resource
    	{   
    		if(p.name.length()>20) {
    			return false;
    		}
    			String sql = String.format("insert into USERS values('%s','%s','%s',0);"
    					//,library.book_number,library.book_name,library.book_author);
    					,p.name,p.password,p.identity,p.name);
    			s.execute(sql);             
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    	return true;
	}
    public boolean delete_Person(String name) {
    	String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
  
        try (
            Connection c = DriverManager.getConnection(url,loginName,password);//连接数据库
            Statement s = c.createStatement(); //用于执行sql语句             
        )//try with resource
        {        	
        		String sql = String.format("delete from USERS where name = '%s' and identity='Customer';",name);
                s.execute(sql);             
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean amend_money(String name,float change) {
    	String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        try (
            Connection c = DriverManager.getConnection(url,loginName,password);//连接数据库
            Statement s = c.createStatement(); //用于执行sql语句             
        )//try with resource
        {        	
        	if(change>0) {
            	String sql = String.format("update USERS set money = money+%f where name = '%s';",change,name);
                s.execute(sql); 
        	}
        	else {
				return false;
			}
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
 
    public boolean is_Customer(String name,String password0) {
    	String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        try (
            Connection c = DriverManager.getConnection(url,loginName,password);//连接数据库
            Statement s = c.createStatement(); //用于执行sql语句             
        )//try with resource
        {        	
        		String sql = String.format("select * from USERS where name = '%s' and password = '%s' and identity='Customer';",name,password0);
        		ResultSet rs = s.executeQuery(sql);
        		if(rs.next()) {
        			return true;
        		}
        		return false;               
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean is_Admin(String name,String password0) {
    	String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        try (
            Connection c = DriverManager.getConnection(url,loginName,password);//连接数据库
            Statement s = c.createStatement(); //用于执行sql语句             
        )//try with resource
        {        	
        		String sql = String.format("select * from USERS where name = '%s' and password = '%s' and identity='Admin';",name,password0);                
        		ResultSet rs = s.executeQuery(sql);
        		if(rs.next()) {
        			return true;
        		}
        		return false;               
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String show_money(String username) {
    	String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        try (
            Connection c = DriverManager.getConnection(url,loginName,password);//连接数据库
            Statement s = c.createStatement(); //用于执行sql语句             
        )//try with resource
        {        	
        	String sql = String.format("select * from USERS where name=%s;",username);
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) {
				String money = rs.getString("money");
				return money;
			}			
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "can't find right now";
    }
    
    public void insertOrder(String bName,String Cname,String cMoney,String localTime) {
    	String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        try (
            Connection c = DriverManager.getConnection(url,loginName,password);//连接数据库
            Statement s = c.createStatement(); //用于执行sql语句             
        )//try with resource
        {        	
        	String sql = String.format("insert into OrderList values(NULL,'%s','%s','%s','%s');"
					//,library.book_number,library.book_name,library.book_author);
					,bName,Cname,cMoney,localTime);
			s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    double searchToOrder(String[][] books) {
    	String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
  
        try (
            Connection c = DriverManager.getConnection(url,loginName,password);//连接数据库
            Statement s = c.createStatement(); //用于执行sql语句             
        )//try with resource
        {       int index = 0; 
        		double saleAmount = 0;
        		String sql = String.format("select * from OrderList;");
        		ResultSet rs = s.executeQuery(sql);  
        		while (rs.next()) {
                    String id = rs.getString("id");// 可以使用字段名
                    String name = rs.getString("bookName");
                    String number  = rs.getString("customer");
                    String author = rs.getString("cost");
                    String cost = rs.getString("time"); 					 
   					books[index][0] = id;
   					books[index][1] = name;
   					books[index][2] = author;
   					books[index][3] = number;
   					books[index][4] = cost;
   					double temp = new Double(books[index][3]);
   					saleAmount += temp;
   					index++;
   				}
        		return saleAmount;
   		} catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    
}