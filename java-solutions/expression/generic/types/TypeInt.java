package expression.generic.types;
import expression.exceptions.*;
import expression.exceptions.Overflow;


public class TypeInt extends ProType<Integer> implements Operations<Integer> {
    private final boolean NeedCheck;

    public TypeInt(boolean needCheck) {
        NeedCheck = needCheck;
    }

    @Override
    public Integer add(Integer a, Integer b) {
        if (NeedCheck) {
            return new CheckedAdd().evalOperation(a, b);
        }
        return super.add(a, b, Integer::sum);
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        if (NeedCheck) {
            return new CheckedDivide().evalOperation(a, b);
        }
        return super.divide(a, b, (x, y) -> x / y);
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        if (NeedCheck) {
            return new CheckedMultiply().evalOperation(a, b);
        }
        return super.multiply(a, b, (x, y) -> x * y);
    }

    @Override
    public Integer negate(Integer a) {
        if (NeedCheck) {
            return new CheckedNegate().evalOperation(a);
        }
        return super.negate(a, x -> -x);
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        if (NeedCheck) {
            return new CheckedSubtract().evalOperation(a, b);
        }
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
        if (NeedCheck && a == Integer.MIN_VALUE){
            throw new Overflow(a, "abs");
        }
        else{
            return super.abs(a, Math::abs);
        }
    }

    @Override
    public Integer square(Integer a) {
        if (NeedCheck && (a > 0 && Integer.MAX_VALUE / a < a || a < 0 && Integer.MAX_VALUE / a > a)) {
            throw new Overflow(a, "*");
        }
        return super.square(a, x -> x * x);
    }

    @Override
    public Integer mod(Integer a, Integer b) {
        return super.mod(a, b, (x, y) -> x % y);
    }
}
