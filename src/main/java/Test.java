import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Integer floor = set.floor(2);
        System.out.println(floor);
        Integer ceiling = set.ceiling(2);
        System.out.println(ceiling);
    }
}
