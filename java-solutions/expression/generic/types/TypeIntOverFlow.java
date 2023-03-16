package expression.generic.types;
import expression.exceptions.Overflow;


public class TypeIntOverFlow extends ProType<Integer> implements Operations<Integer> {

    @Override
    public Integer add(Integer a, Integer b) {
        if (b > 0 ? a > Integer.MAX_VALUE - b : a < Integer.MIN_VALUE - b) {
            throw new Overflow(a + " + " + b);
        }
        return super.add(a, b, Integer::sum);
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new Overflow(a + " / " + b);
        }
        return super.divide(a, b, (x, y) -> x / y);
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        if (a > 0 && b > 0 && Integer.MAX_VALUE / a < b ||
                a < 0 && b < 0 && Integer.MAX_VALUE / a > b ||
                a > 0 && b < 0 && Integer.MIN_VALUE / a > b ||
                a < 0 && b > 0 && Integer.MIN_VALUE / b > a) {
            throw new Overflow(a, b, "*");
        }
        return super.multiply(a, b, (x, y) -> x * y);
    }

    @Override
    public Integer negate(Integer a) {
        if (a == Integer.MIN_VALUE) {
            throw new Overflow("-" + a);
        }
        return super.negate(a, x -> -x);
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        if ((b > 0 && a < Integer.MIN_VALUE + b) || (b < 0 && a > Integer.MAX_VALUE + b)) {
            throw new Overflow(a,b,"-");
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
        if (a == Integer.MIN_VALUE){
            throw new Overflow(a, "abs");
        }
        else{
            return super.abs(a, Math::abs);
        }
    }

    @Override
    public Integer square(Integer a) {
        if (a > 0 && Integer.MAX_VALUE / a < a || a < 0 && Integer.MAX_VALUE / a > a) {
            throw new Overflow(a, "*");
        }
        return super.square(a, x -> x * x);
    }

    @Override
    public Integer mod(Integer a, Integer b) {
        return super.mod(a, b, (x, y) -> x % y);
    }
}
