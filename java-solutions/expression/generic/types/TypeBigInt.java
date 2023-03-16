package expression.generic.types;


import java.math.BigInteger;

public class TypeBigInt extends ProType<BigInteger> implements Operations<BigInteger> {
    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return super.add(a, b, BigInteger::add);
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) {
        return super.divide(a, b, BigInteger::divide);
    }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return super.multiply(a, b, BigInteger::multiply);
    }

    @Override
    public BigInteger negate(BigInteger a) {
        return super.negate(a, BigInteger::negate);
    }

    @Override
    public BigInteger subtract(BigInteger a, BigInteger b) {
        return super.subtract(a, b, BigInteger::subtract);
    }

    @Override
    public BigInteger convert(int a) {
        return BigInteger.valueOf(a);
    }

    @Override
    public BigInteger convert(String a) {
        return new BigInteger(a);
    }

    @Override
    public BigInteger abs(BigInteger a) {
        return super.abs(a, BigInteger::abs);
    }

    @Override
    public BigInteger square(BigInteger a) {
        return super.square(a, (x) -> x.multiply(x));
    }

    @Override
    public BigInteger mod(BigInteger a, BigInteger b) {
        return super.mod(a, b, BigInteger::mod);
    }

}
