package expression.generic;

import expression.generic.types.Operations;

public class Square<T> extends UnaryOperations<T>{
    private final Operations<T> type;

    public Square(AllExpressions<T> expression1, Operations<T> type) {
        super(expression1, "square");
        this.type  = type;
    }

    @Override
    protected T evalOperation(T x) {
        return type.square(x);
    }
}
