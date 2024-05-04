import java.util.ArrayList;

public class Stack <T> {
    private ArrayList <T> stack;

    public Stack() {
        stack = new ArrayList<>();
    }
    public void push(T element) {
        stack.add(element);
    }
    public T pop() {
        if(!stack.isEmpty()){
            T finalElement = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            return finalElement;
        }
        return null;
    }
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        stack.push("Hello");

        String s = stack.pop();

        stack.isEmpty() ;// return true if stack is empty
    }
}