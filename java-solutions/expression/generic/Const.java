package expression.generic;


import expression.generic.types.Operations;

public class Const<T> implements AllExpressions<T> {
    final private T constant;

    public Const(String constant, Operations<T> type) {
        this.constant = type.convert(constant);

    }



    @Override
    public boolean equals(Object expression) {
        if (this == expression) {
            return true;
        }
        if (expression == null || getClass() != expression.getClass()) {
            return false;
        }
        Const<?> consto = (Const<?>) expression;
        return constant == consto.constant;

    }

    @Override
    public int hashCode() {
        return constant.hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(constant);
    }

    @Override
    public T evaluate(T x) {
        return null;
    }

    @Override
    public  T evaluate(T x, T y, T z) {
        return constant;
    }
}
