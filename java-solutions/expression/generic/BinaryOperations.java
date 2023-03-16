package generic;


import expression.ToMiniString;


import java.util.Objects;


public abstract class BinaryOperations<T> extends BinaryEvaluate<T> implements AllExpressions<T>, ToMiniString {

    private final AllExpressions<T> expression1;
    private final AllExpressions<T> expression2;
    private final String op;

    protected BinaryOperations(AllExpressions<T> expression1, AllExpressions<T> expression2, String op) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.op = op;
    }

    @Override
    public boolean equals(Object expression) {
        if (this == expression) {
            return true;
        }
        if (expression == null || getClass() != expression.getClass()) {
            return false;
        }
        BinaryOperations<?> bp = (BinaryOperations<?>) expression;
        return expression1.equals(bp.expression1) && expression2.equals(bp.expression2) && op.equals(bp.op);
    }
    @Override
    public int hashCode() {
        return Objects.hash(expression1, expression2, op);
    }
    @Override
    public T evaluate(T x) {
        return evalOperation(expression1.evaluate(x), expression2.evaluate(x));
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return  evalOperation(expression1.evaluate(x, y, z), expression2.evaluate(x, y, z));
    }
    @Override
    public String toString() {
        return "(" + expression1.toString() + " " + op + " " + expression2.toString() + ")";
    }


}