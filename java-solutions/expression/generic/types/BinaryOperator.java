package expression.generic.types;
@FunctionalInterface
interface BinaryOperator<T>{
    T apply(T a, T b);
}
