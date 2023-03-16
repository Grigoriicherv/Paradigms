package generic;


import expression.exceptions.ParsingException;
import generic.types.Operation;

import java.math.BigInteger;


public class Main {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int x = sc.nextInt();
//        int y = sc.nextInt();
//        int z = sc.nextInt();

        //ExpressionParser<Double> expr = new ExpressionParser<>();
        Short a = 8;
        Short b = 7;
        Operation.doBiOperation(a, b, (x, y) -> (short) (x + y));


    }
}
