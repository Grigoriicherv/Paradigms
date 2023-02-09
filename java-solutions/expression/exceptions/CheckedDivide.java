package expression.exceptions;

import expression.AllExpressions;
import expression.Divide;

public class CheckedDivide extends Divide {
    public CheckedDivide(AllExpressions expression1, AllExpressions expression2) {
        super(expression1, expression2);
    }
    @Override
    protected int evalOperation (int x, int y){
        if (y == 0) {
            throw new DivisionByZero("y must be not 0");
        }else if(x == Integer.MIN_VALUE && y==-1){
            throw  new Overflow(x,y,"/");
        }
        return x / y;
    }
}
