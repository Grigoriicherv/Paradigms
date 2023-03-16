package expression.generic.types;

public abstract class ProType<T> implements Operations<T>{
    public T add(T a, T b, BinaryOperator<T> op) {
        return Operation.doBiOperation(a, b, op);
    }
    public T divide(T a, T b, BinaryOperator<T> op) {
        return Operation.doBiOperation(a, b, op);
    }
    public T multiply(T a, T b, BinaryOperator<T> op) {
        return Operation.doBiOperation(a, b, op);
    }
    public T negate(T a, UnaryOperator<T> op) {
        return Operation.doUniOperation(a, op);
    }
    public T subtract(T a, T b, BinaryOperator<T> op) {
        return Operation.doBiOperation(a, b, op);
    }
    public T abs (T a, UnaryOperator<T> op) {
        return Operation.doUniOperation(a, op);
    }
    public T square (T a, UnaryOperator<T> op){
        return Operation.doUniOperation(a, op);
    }
    public T mod (T a, T b, BinaryOperator<T> op) {
        return Operation.doBiOperation(a, b, op);
    }
}
