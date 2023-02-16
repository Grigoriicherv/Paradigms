package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface CharSource {
    boolean hasNext();

    String prit();
    char next();
    int getPos();
    IllegalArgumentException error(String message);
}
