public class Addition extends BinaryExpression {
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
}
