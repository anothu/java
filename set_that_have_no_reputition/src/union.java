import java.util.HashSet;
import java.util.Set;

public class union {
	public static void main(String[] args) {
		String[] Arr1 = {"1","2","3"};
		String[] Arr2 = {"2","3","4"};
		String[] result = combine(Arr1, Arr2);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
		
	}



public static String[] combine(String[] arr1,String[] arr2) {
	Set<String> set = new HashSet<String>();
	for(String str : arr1) {
		set.add(str);
	}
	for(String str : arr2) {
		set.add(str);
	}
	String[] result = {};
	return  set.toArray(result);
}
}