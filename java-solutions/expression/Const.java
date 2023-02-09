package expression;

public class Const implements AllExpressions, Expression, TripleExpression {

    final private int constant;

    public Const(int constant) {
        this.constant = constant;
    }



    @Override
    public int evaluate(int x) {
        return constant;
    }



    @Override
    public boolean equals(Object expression) {
        if (this == expression) {
            return true;
        }
        if (expression == null || getClass() != expression.getClass()) {
            return false;
        }
        Const consto = (Const) expression;
        return constant == consto.constant;

    }

    @Override
    public int hashCode() {
        return constant * 31;
    }

    @Override
    public String toString() {
        return String.valueOf(constant);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return constant;
    }

}
