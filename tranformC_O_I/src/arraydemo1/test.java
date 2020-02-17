package arraydemo1;

public class test {
	interface farmer{}
	public static class fruit implements farmer{
		public String name;
	}


	public static class orange extends fruit {
		
	}
	public static void main(String[] args) {
		fruit a = new fruit();
		orange b = (orange)a;//父类转子类
		
		orange c = new orange();
		fruit d = c;//子类转父类（orange is fruit）
		
		orange e = new orange();
		farmer f = e;//类转接口
		orange g = (orange)f;//接口转类
		
		System.out.println(a instanceof orange);//判断a对象是否为orange类型
		
	}
}