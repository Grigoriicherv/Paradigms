package expression.generic.types;
@FunctionalInterface
public interface UnaryOperator<T>{
    T apply(T a);
}
