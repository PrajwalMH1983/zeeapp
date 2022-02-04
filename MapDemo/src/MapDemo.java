import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;

public class MapDemo {
	public static void main(String[] args) {
		
		
		TreeMap<Integer, String> map = new TreeMap<>();
		map.put(10, "Prajwal");
		
		//Here 10 (Object) ===> Integer ==> Internally it will interpret as
		//new Integer(10) ==> autoboxing ( Conversion of primitive to Objects)
		//Unboxing ==> Object to Primitive
		
		map.put(-2, null);
		map.put(-1, "Hardekar");
		map.put(1, "H");
		map.put(100, "a");
		map.put(-10, "r");
		map.put(50, "d");
		map.put(0, "Prajwal");
		System.out.println(map);
		
		//Extract only keys
		System.out.println(map.keySet());
		
		Set<Integer> sets = map.keySet();
		System.out.println(map.containsKey(1000)); 
		
		//retrieve all values
		//Using values() method
		//How to traverse the map using forEach method
		map.forEach((k , v)-> {
			System.out.println(k + " : " + v);
		});
	}
}
