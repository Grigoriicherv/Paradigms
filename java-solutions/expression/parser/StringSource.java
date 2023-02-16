package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class StringSource implements CharSource {
    public final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }
    @Override
    public int getPos(){
        return pos;
    }
    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public String prit (){
       return data;
    }
    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public IllegalArgumentException error(final String message) {
        return new IllegalArgumentException(pos + ": " + message+ "|"+data+"|");
    }
}
