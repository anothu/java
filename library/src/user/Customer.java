package user;

public class Customer extends Person{
	float money;
	public Customer(String n, String p, String i,float m) {
		name = n;
		password = p;
		identity = i;
		money = m;
	}
}
