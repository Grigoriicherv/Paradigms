package expression;

import java.util.Arrays;

public class Clear extends BinaryOperations{

    public Clear (AllExpressions expression1, AllExpressions expression2) {
        super(expression1, expression2, "clear");
    }

    @Override
    protected int evalOperation(int x, int y) {
        return x & ~(1 << y);
    }

}
