public class Addition implements BinaryExpression {
    private Expression left,right;
    public Addition(Expression l, Expression r){
        left = l;
        right = r;
    }
    public int evaluate(){
        return left.evaluate()+right.evaluate();
    }
    public String toString(){
        return (left + " + " + right);
    }

    @Override
    public Expression left() {
        return left;
    }

    @Override
    public Expression right() {
        return null;
    }
}
