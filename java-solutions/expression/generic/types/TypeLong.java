package expression.generic.types;

public class TypeLong extends ProType<Long> implements Operations<Long> {
    @Override
    public Long add(Long a, Long b) {
        return super.add(a, b, Long::sum);
    }

    @Override
    public Long divide(Long a, Long b) {
        return super.divide(a, b, (x, y) -> x / y);
    }

    @Override
    public Long multiply(Long a, Long b) {
        return super.multiply(a, b, (x, y) -> x * y);
    }

    @Override
    public Long negate(Long a) {
        return super.negate(a, x -> -x);
    }

    @Override
    public Long subtract(Long a, Long b) {
        return super.subtract(a, b, (x, y) -> x - y);
    }

    @Override
    public Long convert(int a) {
        return (long) a;
    }

    @Override
    public Long convert(String a) {
        return Long.parseLong(a);
    }

    @Override
    public Long abs(Long a) {
        return super.abs(a, Math::abs);
    }

    @Override
    public Long square(Long a) {
        return super.square(a, (x) -> x * x);
    }

    @Override
    public Long mod(Long a, Long b) {
        return super.mod(a, b, (x, y) -> x % y);
    }
}
