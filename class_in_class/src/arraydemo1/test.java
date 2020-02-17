package arraydemo1;

public class test{
	class mathexam{
		void grade() {
			System.out.println("you get zero point!");
		}
	}
	static class historyexam{
		void grade() {
			System.out.println("you get 100 points");
		}
	}
	
	public static void main(String[] args) {
		test a = new test();
		//test.mathexam b = new test.mathexam();错误
		mathexam b = a.new mathexam();//内部非静态类只有在外部类创建对象后才能使用
		b.grade();
		test.historyexam c = new test.historyexam();
		c.grade();//内部静态类无需外部类创建对象
	}
}