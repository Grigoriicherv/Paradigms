package expression.generic;



import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(Arrays.deepToString(new GenericTabulator().tabulate(args[0].substring(1), args[1],
                    -2, 2, -2,
                    2, -2, 2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
