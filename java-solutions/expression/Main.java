package expression;

import expression.exceptions.Overflow;
import expression.exceptions.ParsingException;
import expression.parser.ExpressionParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int x = sc.nextInt();
//        int y = sc.nextInt();
//        int z = sc.nextInt();

        ExpressionParser expr = new ExpressionParser();

        try {
            System.out.println(expr.parse("5set5").evaluate(1, 2, 3));
        } catch (ParsingException e) {
            throw new RuntimeException(e);
        }


    }
}
