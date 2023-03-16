package expression.generic.types;
@FunctionalInterface
interface UnaryOperator<T>{
    T apply(T a);
}
