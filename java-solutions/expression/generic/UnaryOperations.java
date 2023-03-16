package expression.generic;



import java.util.Objects;

public abstract class UnaryOperations<T> extends UnaryEvaluate<T> implements AllExpressions<T>{
    private  final AllExpressions<T> expression;
    private final String op;

    protected UnaryOperations(AllExpressions<T> expression, String op) {
        this.expression = expression;
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
        UnaryOperations<?> bp = (UnaryOperations<?>) expression;
        return expression.equals(bp.expression) && op.equals(bp.op);
    }
    @Override
    public int hashCode() {
        return Objects.hash(expression, op);
    }
    @Override
    public T evaluate(T x) {
        return evalOperation(expression.evaluate(x));
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return  evalOperation(expression.evaluate(x, y, z));
    }
    @Override
    public String toString() {
        return (op + " " + expression.toString()) ;
    }
}
