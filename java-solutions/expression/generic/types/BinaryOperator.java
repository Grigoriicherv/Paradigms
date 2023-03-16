package expression.generic.types;
@FunctionalInterface
public interface BinaryOperator<T>{
    T apply(T a, T b);
}
