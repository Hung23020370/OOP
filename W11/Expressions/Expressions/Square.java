
public class Square extends Expression {
    private Expression expression;

    public Square(Expression exp) {
        this.expression = exp;
    }

    @Override
    public int evaluate() {
        int value = expression.evaluate();
        return value * value;
    }

    @Override
    public String toString() {
        return "(" + expression.toString() + ")^2";
    }
}
