package expression.exceptions;

import expression.AllExpressions;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(AllExpressions expression1, AllExpressions expression2) {
        super(expression1, expression2);
    }
    @Override
    protected int evalOperation (int x, int y){
        if (x > 0 && y > 0 && Integer.MAX_VALUE / x < y ||
                x < 0 && y < 0 && Integer.MAX_VALUE / x > y ||
                x > 0 && y < 0 && Integer.MIN_VALUE / x > y ||
                x < 0 && y > 0 && Integer.MIN_VALUE / y > x) {
            throw new Overflow(x, y, "*");
        }

        return x * y;
    }
}