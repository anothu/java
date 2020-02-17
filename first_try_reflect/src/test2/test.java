package test2;
import qwe.person;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class test {
	public static void main(String[] args) {
		try {
		//创建对象
		String class_name = "qwe.person";
		Class pClass = Class.forName(class_name);
		Constructor c = pClass.getConstructor();
		person p1 = (person) c.newInstance();
		//修改属性
		Field f1 = p1.getClass().getDeclaredField("name");
		f1.set(p1, "Tom");
		System.out.println(p1.name);
		//调用方法
		Method m = p1.getClass().getMethod("setname", String.class);
		m.invoke(p1, "Jerry");
		System.out.println(p1.name);
	} catch (Exception e) {
		e.printStackTrace();	
	}
	}
	
}
