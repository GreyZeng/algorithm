import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
//        TreeMap<Integer, Integer> map = new TreeMap<>();
//        map.put(3, 4);
//        map.put(3, 54);
//        Integer integer = map.get(3);
//        System.out.println(integer);
        
        TreeSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(12);
        System.out.println(set.floor(11));
    }
}
