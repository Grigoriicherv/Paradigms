package expression;

import java.util.Arrays;

public class Set extends BinaryOperations{



    public Set (AllExpressions expression1, AllExpressions expression2) {
        super(expression1, expression2, "set");
    }

    @Override
    protected int evalOperation(int x, int y) {
        return x | (1 << y);
    }
}
