import java.util.*;

public class Taboo <T> {
    private T [] rule ;
    public Taboo(T [] rule) {
        this.rule = rule;
    }

    public static void main(String[] args) {
        List <String> list = List.of("a","c","b","x","c","a");
        String [] rule = {"a","c","a","b"};
        Taboo<String> taboo = new Taboo<>(rule);
        System.out.println(taboo.noFollow("b"));
        System.out.println(taboo.reduce(list));
    }

    private <T> List <T> reduce(List <T> list) {
        // Check if function is empty, return [ ]
        if (list == null) {
            return Collections.emptyList();
        }
        List <T> NewList = new ArrayList<>();
        // Add the first character because it is always correct
        NewList.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            T t = list.get(i);
            T u = NewList.get(NewList.size() - 1);
            //The "contains()" function checks whether noFollow(u) contains t or not.
            // If not, add t to the NewList
            if(!noFollow(u).contains(t)) {
                NewList.add(t);
            }
        }
        return NewList;
    }

    private  <T> Set noFollow(T str) {
        Set <T> list = new HashSet<>();
        for (int i = 0; i < rule.length -1 ; i++) {
            //Compare str and rule[i]
            // If so, add ordinal characters (i + 1)
            if(Objects.equals(str,rule[i]))  list.add((T) rule[i+1]);
        }
        // Check if function is empty, return [ ]
        if(list.isEmpty()) {
            return Collections.emptySet();
        }
        return list;
    }
}
