package iterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class iterator {
	public static void main(String[] args) {
		List<String> ll = new LinkedList<String>();
		ll.add("first");
		ll.add("second");
		ll.add("third");
		ll.add("fourth");
		boolean has_second = false;
		for(Iterator<String> it = ll.iterator();it.hasNext();){
			String string = (String)it.next();
			System.out.println(string);
			/*if(string.equals("second"))
			 	ll.remove("second");
				ll.add("fifth");
			抛出上述异常的主要原因是当条用容器的iterator()方法返回Iterator对象时，
			把容器中包含对象的个数赋值给了一个变量expectedModCount，
			在调用next()方法时会比较变量expectedModCount与容器中实际对象的个数modCount的值是否相等，
			若二者不相等，则会抛出ConcurrentModificationException异常
			*/
			if(string.equals("second"))
				has_second = true;
		}
		if(has_second)
			ll.add("fifth");
			ll.remove("second");
		System.out.println();
		for(Iterator<String> it = ll.iterator();it.hasNext();){
			String string = (String)it.next();
			System.out.println(string);
		}
	}
}
