package arraydemo1;

import java.util.ArrayList;
import java.util.Random;

class demo{
	int a;
	double b;
	String c;
	public demo(int a,double b,String c) {
		// TODO Auto-generated constructor stub
		this.a = a;
		this.b = b;
		this.c = c;
		
	}
}

interface checker{
	public boolean exam(demo d);
}

public class test{
	public static void main(String[] args) {
		Random r = new Random();
		ArrayList<demo> demos = new ArrayList<demo>();
		for (int i = 0; i < 5; i++) {
			demos.add(new demo(r.nextInt(100),r.nextDouble()*5,"nothingnew"));
		}
		filter(demos, h->h.a<50 && h.b<2.5);
		
		
	}
	private static void filter(ArrayList<demo> a,checker b) {
		for(demo i : a) {
			if(b.exam(i))
				System.out.println(i.a + " " + i.b);
		}
		
	}
}