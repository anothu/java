package arraydemo1;

interface fruit {
	public void eat();
	public void add();
}
class apple implements fruit{
	public int amount;
	public synchronized void eat() {
		if(amount == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		amount-=2;
		if(amount == -1) {
			amount = 0;
		}
		System.out.println("apple is being eaten,now we have " + amount + "apples.");	
	}
	public synchronized void add() {
		amount++;
		System.out.println("apple number is increasing, now we have " + amount + "apples.");
		if(amount > 5) {
			this.notify();
		}
	}
	public boolean is_empty() {
		if(amount==0) {
			return true;
		}
		return false;
	}
}

public class test{
	public static void main(String[] args) {
		apple a = new apple();
		a.amount = 5;
		Thread t1 = new Thread() {
			public void run() {
				while(true) {
					a.eat();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t1.start();
		
		Thread t2 = new Thread() {
			public void run() {
				while(true) {
					a.add();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t2.start();
	}
}


