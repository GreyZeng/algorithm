import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {
    	TreeMap<Integer,Integer> map = new TreeMap<>();
    	map.put(1, 2);
    	map.put(1, 3);
    	map.put(2, 2);
    	System.out.println(map);
    	System.out.println("hello 测试");
    }
}
