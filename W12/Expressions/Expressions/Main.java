public class Main {
    public static void main(String[] args) {
        // 1^2 + 2^2
        Numeral one = new Numeral(1);
        Numeral two = new Numeral(2);
        Square one_square = new Square(one);
        Square two_square = new Square(two);
        Addition e1 = new Addition(one_square, two_square);
        System.out.print(e1.toString() + " = " + e1.evaluate());
    }
}

