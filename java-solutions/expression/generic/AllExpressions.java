package generic;



public interface AllExpressions<T> {
     T evaluate(T x);

     T evaluate(T x, T y, T z);

}
