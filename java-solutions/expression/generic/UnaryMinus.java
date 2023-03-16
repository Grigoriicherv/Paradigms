package generic;


import generic.types.Operations;

public class UnaryMinus<T> extends UnaryOperations<T> {
    private final Operations<T> type;

    public UnaryMinus(AllExpressions<T> expression1, Operations<T> type) {
        super(expression1, "square");
        this.type  = type;
    }

    @Override
    protected T evalOperation(T x) {
        return type.negate(x);
    }

}
