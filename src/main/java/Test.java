import java.util.TreeMap;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(3, 4);
        map.put(3, 54);
        Integer integer = map.get(3);
        System.out.println(integer);
    }
}
