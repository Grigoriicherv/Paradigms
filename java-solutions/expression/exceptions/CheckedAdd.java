package expression.exceptions;

import expression.Add;
import expression.AllExpressions;

public class CheckedAdd extends Add {
    public CheckedAdd(AllExpressions expression1, AllExpressions expression2) {
        super(expression1, expression2);
    }
    @Override
    protected int evalOperation (int x, int y) {
        int sum = x + y;
        if (((x ^ sum) & (y ^ sum)) < 0) {
            throw new Overflow(x, y, "+");
        }
        return x + y;
    }
}
