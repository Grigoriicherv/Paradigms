package expression.exceptions;

import expression.AllExpressions;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(AllExpressions expression1, AllExpressions expression2) {
        super(expression1, expression2);
    }
    @Override
    protected int evalOperation (int x, int y){
        if (x != 0 && y != 0 && ((x * y) / y != x || (x * y) / x != y)) {
            throw new Overflow(x, y, "*");
        }

        return x * y;
    }
}