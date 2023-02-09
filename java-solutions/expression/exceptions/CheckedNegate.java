package expression.exceptions;

import expression.AllExpressions;
import expression.UnaryMinus;

public class CheckedNegate extends UnaryMinus {
    public CheckedNegate(AllExpressions expression1) {
        super(expression1);
    }
    @Override
    protected int evalOperation (int x){
        if (x == Integer.MIN_VALUE) {
            throw new Overflow(x,"This number is too big");
        }
        return -1 * x;
    }
}