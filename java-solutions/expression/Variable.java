package expression;

public class Variable implements AllExpressions, Expression, TripleExpression {
    final private String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public boolean equals(Object expression) {
        if (this == expression) return true;
        if (expression == null || getClass() != expression.getClass()) return false;
        Variable var = (Variable) expression;
        return variable.equals(var.variable);
    }

    @Override
    public int hashCode() {
        return variable.hashCode();
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (variable) {
            case ("x"):
                return x;
            case ("y"):
                return y;
            case ("z"):
                return z;
            default:
                return 0;
        }
    }
}
