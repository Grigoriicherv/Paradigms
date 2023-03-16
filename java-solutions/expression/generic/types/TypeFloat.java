package expression.generic.types;

public class TypeFloat extends ProType<Float> implements Operations<Float>{
    @Override
    public Float add(Float a, Float b) {
        return super.add(a, b, Float::sum);
    }

    @Override
    public Float divide(Float a, Float b) {
        return super.divide(a, b, (x, y) -> (float )x / y);
    }

    @Override
    public Float multiply(Float a, Float b) {
        return super.multiply(a, b, (x, y) -> x * y);
    }

    @Override
    public Float negate(Float a) {
        return super.negate(a, x -> -x);
    }

    @Override
    public Float subtract(Float a, Float b) {
        return super.subtract(a, b, (x, y) -> x - y);
    }

    @Override
    public Float convert(int a) {
        return (float) a;
    }

    @Override
    public Float convert(String a) {
        return Float.parseFloat(a);
    }

    @Override
    public Float abs(Float a) {
        return super.abs(a, Math::abs);
    }

    @Override
    public Float square(Float a) {
        return super.square(a, (x) -> x * x);
    }

    @Override
    public Float mod(Float a, Float b) {
        return super.mod(a, b, (x, y) -> x % y);
    }
}
