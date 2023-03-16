package expression.generic;


public abstract class BinaryEvaluate<T> {
    protected abstract T evalOperation(T x, T y);
}
