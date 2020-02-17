package user;

public abstract class Person {
	public String name;
	public String password;
	public String identity;	
	public Person(){		
	}
}

class Admin extends Person{
	public Admin(String n, String p, String i) {
		name = n;
		password = p;
		identity = i;
	}
}


