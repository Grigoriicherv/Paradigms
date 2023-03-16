package expression.generic.types;


public class TypeDouble extends ProType<Double> implements Operations<Double> {

    @Override
    public Double add(Double a, Double b) {
        return super.add(a, b, Double::sum);
    }

    @Override
    public Double divide(Double a, Double b) {
        return super.divide(a, b, (x, y) -> x / y);
    }

    @Override
    public Double multiply(Double a, Double b) {
        return super.multiply(a, b, (x, y) -> x * y);
    }

    @Override
    public Double negate(Double a) {
        return super.negate(a, x -> -x);
    }

    @Override
    public Double subtract(Double a, Double b) {
        return super.subtract(a, b, (x, y) -> x - y);
    }

    @Override
    public Double convert(int a) {
        return (double)a;
    }

    @Override
    public Double convert(String a) {
        return Double.parseDouble(a);
    }

    @Override
    public Double abs(Double a) {
        return super.abs(a, Math::abs);
    }

    @Override
    public Double square(Double a) {
        return super.square(a, (x) -> x * x);
    }

    @Override
    public Double mod(Double a, Double b) {
        return super.mod(a, b, (x, y) -> x % y);
    }

}
