import java.util.HashMap;


public class Hashmaptest {

	public static void main(String[] args){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("Test",	 1);
		
		int val = map.get("Test");
		map.put("Test", val + 1);
	}
}
