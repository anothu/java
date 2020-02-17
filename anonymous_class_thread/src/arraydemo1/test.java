package arraydemo1;

abstract class fruit {
	int amount = 500;
	void eat() {};
	boolean is_empty() {return false;};
}

class orange extends fruit{
	public void eat() {
		System.out.println("eating orange...");
		amount--;
	}
	public boolean is_empty() {
		if(amount==0) {
			System.out.println("no orange");
			return true;
		}
		return false;
	}
}

class apple extends fruit{
	public void eat() {
		System.out.println("eating apple");
		amount--;
	}	
	public boolean is_empty() {
		if(amount==0) {
			System.out.println("no apple");
			return true;
		}
		return false;
	}
}

public class test{
	public static void main(String[] args) {
		apple a = new apple();
		orange o = new orange();
		Thread t1 = new Thread() {
			public void run() {
				while(!a.is_empty()) {
					a.eat();
				}
			}
		};
		t1.start();
		Thread t2 = new Thread() {
			public void run() {
				while(!o.is_empty()) {
					o.eat();
				}
			}
		};
		t2.start();
	}
}
