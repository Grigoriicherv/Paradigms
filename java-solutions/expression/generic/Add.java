package expression.generic;

import expression.generic.types.Operations;


public class Add<T> extends BinaryOperations<T>{
    private final Operations<T> type;

    public Add(AllExpressions<T> expression1, AllExpressions<T> expression2, Operations<T> type) {
        super(expression1, expression2, "+");
        this.type  = type;
    }

    @Override
    protected T evalOperation(T x, T y) {
        return type.add(x, y);
    }

}
