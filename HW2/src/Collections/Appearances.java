import java.util.*;

public class Appearances {
    public static void main(String[] args) {
        // Khai báo một list với các phần tử có sẵn
        // Collection là một tập hợp chung chung
        Collection<String> list1 = List.of("a", "b", "a", "b", "c");
        Collection<String> list2 = List.of("c", "a", "a", "d", "b", "b", "b");
        int as = sameCount(list1, list2);
        System.out.print(as);
    }

    // Khai báo hàm với kiểu dữ liệu generic T
    private static <T> int sameCount(Collection<T> list1, Collection<T> list2) {
        // Khai báo Map trong java
        Map<T, Integer> map1 = new HashMap<>();
        Map<T, Integer> map2 = new HashMap<>();
        for (T i : list1) {
            // Giống như map trong C++
            map1.put(i, map1.getOrDefault(i, 0) + 1);
        }
        for (T i : list2) {
            // Giống như map trong C++
            map2.put(i, map2.getOrDefault(i, 0) + 1);
        }
        int cnt = 0;
        for (T key : map2.keySet()) {
            if (map2.get(key) == map1.get(key) && map1.containsKey(key) && map2.containsKey(key)) cnt++;
        }
        return cnt;
    }
}
