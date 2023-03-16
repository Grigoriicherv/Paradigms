package expression.generic.types;

public class Operation {
    public static <T> T doBiOperation(T a, T b, BinaryOperator<T> operation) {
        return operation.apply(a, b);
    }
    public static <T> T doUniOperation(T a,  UnaryOperator<T> operation){
        return operation.apply(a);
    }

}
