package expression.generic.types;

public class TypeShort extends ProType<Short> implements Operations<Short> {
    @Override
    public Short add(Short a, Short b) {
        return super.add(a, b, (x, y) -> (short) (x + y));
    }
    @Override
    public Short divide(Short a, Short b) {
        return super.divide(a, b, (x, y) -> (short) (x / y));
    }

    @Override
    public Short multiply(Short a, Short b) {
        return super.multiply(a, b, (x, y) -> (short) (x * y));
    }

    @Override
    public Short negate(Short a) {
        return super.negate(a, x -> (short) (-x));
    }

    @Override
    public Short subtract(Short a, Short b) {
        return super.subtract(a, b, (x, y) -> (short) (x - y));
    }

    @Override
    public Short convert(int a) {
        return (short) a;
    }

    @Override
    public Short convert(String a) {
        return Short.parseShort(a);
    }

    @Override
    public Short abs(Short a) {
        return super.abs(a, (x) -> (short) Math.abs(x));
    }

    @Override
    public Short square(Short a) {
        return super.square(a, (x) -> (short) (x * x));
    }

    @Override
    public Short mod(Short a, Short b) {
        return super.mod(a, b, (x, y) -> (short) (x % y));
    }
}
