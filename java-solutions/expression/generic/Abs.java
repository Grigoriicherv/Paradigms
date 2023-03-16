package expression.generic;

import expression.generic.types.Operations;


public class Abs<T> extends UnaryOperations<T> {
    private final Operations<T> type;

    public Abs(AllExpressions<T> expression1, Operations<T> type) {
        super(expression1, "abs");
        this.type  = type;
    }

    @Override
    protected T evalOperation(T x) {
        return type.abs(x);
    }
}
