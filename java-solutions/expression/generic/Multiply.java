package generic;



import generic.types.Operations;

public class Multiply<T> extends BinaryOperations<T> {

    private final Operations<T> type;

    public Multiply(AllExpressions<T> expression1, AllExpressions<T> expression2, Operations<T> type) {
        super(expression1, expression2, "+");
        this.type  = type;
    }

    @Override
    protected T evalOperation(T x, T y) {
        return type.multiply(x, y);
    }
}
