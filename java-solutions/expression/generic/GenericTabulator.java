package expression.generic;


import expression.exceptions.ParsingException;
import expression.generic.types.*;
import generic.types.*;

import java.util.Map;


public class GenericTabulator implements Tabulator {
    private final Map<String, Operations<?>> tabulators = Map.of(
            "i" , new TypeIntOverFlow(),
            "d" ,  new TypeDouble(),
            "bi" ,  new TypeBigInt(),
            "u", new TypeInt(),
            "s", new TypeShort(),
            "l", new TypeLong(),
            "f", new TypeFloat()
    );
    @Override
    public  Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2){
        return calculate(tabulators.get(mode), expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] calculate(Operations<T> type,
                                       String expression, int x1, int x2, int y1, int y2, int z1, int z2){
        AllExpressions<T> exp;
        try {
            exp = new ExpressionParser<T>().parse(expression, type);
        } catch (ParsingException e) {
            throw new RuntimeException("there is exception");
        }
        Object[][][] tab = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        tab[i - x1][j - y1][k - z1] = exp.evaluate(type.convert(i),
                                type.convert(j), type.convert(k));
                    } catch (RuntimeException ignored) {}
                }
            }
        }
        return tab;
    }
}
