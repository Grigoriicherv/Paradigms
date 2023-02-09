package expression.exceptions;

import expression.AllExpressions;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(AllExpressions expression1, AllExpressions expression2) {
        super(expression1, expression2);
    }
    @Override
    protected int evalOperation (int x, int y){
        if (x < 0 && y > 0 && x - y > 0) {
            throw new Overflow(x,y,"-");
        } else if (x >= 0 && y <= 0 && x - y < 0) {
            throw new Overflow(x,y,"-");
        }
        return x - y;
    }
}