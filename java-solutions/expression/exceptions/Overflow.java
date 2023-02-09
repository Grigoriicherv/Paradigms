package expression.exceptions;

public class Overflow extends ArithmeticException{

    public Overflow(String message){

        super(message);
    }
    public Overflow(int x, int y, String message) {
        super(x +" "+message+" "+y);
    }
    public Overflow(int x, String message) {
        super(x +" "+message);
    }
}
