package expression.generic;


import expression.generic.types.Operations;

public class Divide<T> extends BinaryOperations<T> {

    private final Operations<T> type;

    public Divide(AllExpressions<T> expression1, AllExpressions<T> expression2, Operations<T> type) {
        super(expression1, expression2, "+");
        this.type  = type;
    }

    @Override
    protected T evalOperation(T x, T y) {
        return type.divide(x, y);
    }
}
