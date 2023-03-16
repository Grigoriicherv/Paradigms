package expression.generic.types;

public class TypeInt extends ProType<Integer> implements Operations<Integer> {
    @Override
    public Integer add(Integer a, Integer b) {
        return super.add(a, b, (x, y) -> x + y);
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        return super.divide(a, b, (x, y) -> x / y);
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        return super.multiply(a, b, (x, y) -> x * y);
    }

    @Override
    public Integer negate(Integer a) {
        return super.negate(a, x -> -x);
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        return super.subtract(a, b, (x, y) -> x - y);
    }

    @Override
    public Integer convert(int a) {
        return a;
    }

    @Override
    public Integer convert(String a) {
        return Integer.parseInt(a);
    }

    @Override
    public Integer abs(Integer a) {
        return super.abs(a, Math::abs);
    }

    @Override
    public Integer square(Integer a) {
        return super.square(a, (x) -> x * x);
    }

    @Override
    public Integer mod(Integer a, Integer b) {
        return super.mod(a, b, (x, y) -> x % y);
    }
}
