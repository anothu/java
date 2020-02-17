package search_same_string;

public class SearchCommonString {
	public static void main(String[] args) {
		String searchtext = "That is what we will search.";
		String text = "search";
		int max = searchtext.length() - text.length();
		boolean judge = false;
		testlbl:
			for (int i = 0; i <= max; i++) {
				int length = text.length();
				int j = i;
				int k = 0;
				while(length-- != 0) {
					if(searchtext.charAt(j++) != text.charAt(k++)) {
						continue testlbl;
					}
				}
				judge = true;
				System.out.println("have something in common!");
				break testlbl;
			}
		if(!judge) {
			System.out.println("have no common sense");
		}
		
		
	}
}
