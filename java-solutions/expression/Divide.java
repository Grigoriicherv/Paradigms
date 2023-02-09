package expression;


public class Divide extends BinaryOperations {
    public Divide(AllExpressions expression1, AllExpressions expression2) {
        super(expression1, expression2, "/");
    }
    @Override
    protected int evalOperation(int evExpres1, int evExpres2) {
        return evExpres1 / evExpres2;
    }
}
