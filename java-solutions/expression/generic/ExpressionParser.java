package generic;


import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.StringSource;
import expression.exceptions.*;
import generic.types.Operations;

public final class ExpressionParser<T> {
    public AllExpressions<T> parse(final String source, Operations<T> type) throws ParsingException {
        return  parse(new StringSource(source), type);
    }

    public AllExpressions<T> parse(final CharSource source, Operations<T> type) throws ParsingException {
        return new Expressionsparser<T>(source, type).parseExpressions();
    }

    private static class Expressionsparser<T> extends BaseParser {
        Operations<T> type;
        public Expressionsparser(final CharSource source, Operations<T> type) {
            super(source);
            this.type = type;
        }
        public AllExpressions<T> parseExpressions() throws ParsingException {
            skipWhitespace();
            final AllExpressions<T> result = parseExpression();
            skipWhitespace();
            if (eof()) {
                return result;
            }
            checkExceptionsinExpressions();
            throw new ParsingException ("End of parser");
        }


        private AllExpressions<T> parseExpression() throws ParsingException {
            skipWhitespace();
            AllExpressions<T> result = parseTerm();
            skipWhitespace();
            while (test('+') || test('-')){
                if (take('+')){
                    skipWhitespace();
                    checkExceptionsinExpression();
                    final AllExpressions<T> result2 = parseTerm();
                    result  = new generic.Add<T>(result, result2, type);

                }
                else{
                    take();
                    skipWhitespace();
                    checkExceptionsinExpression();
                    final AllExpressions<T> result2 = parseTerm();
                    result = new generic.Subtract<T>(result, result2, type);

                }
            }
            return result;
        }
        private AllExpressions<T> parseTerm() throws ParsingException {
            skipWhitespace();
            AllExpressions<T> result = parseValue();
            skipWhitespace();
            while (test('*') || test('/') || test('m')){
                if (take('*')){
                    skipWhitespace();
                    checkExceptionsinExpression();
                    final AllExpressions<T> result2 = parseValue();
                    result  = new generic.Multiply<> (result, result2, type);
                    skipWhitespace();
                }
                else if (take('/')) {
                    take();
                    skipWhitespace();
                    checkExceptionsinExpression();
                    final AllExpressions<T> result2 = parseValue();
                    result = new generic.Divide<T> (result, result2, type);
                    skipWhitespace();
                }
                else{
                    expect("mod");
                    skipWhitespace();
                    checkExceptionsinExpression();
                    final AllExpressions<T> result2 = parseValue();
                    result  = new generic.Mod<> (result, result2, type);
                    skipWhitespace();
                }
            }
            return result;
        }

        private AllExpressions<T> parseValue() throws ParsingException {
            skipWhitespace();
            if (take('a')) {
                expect("bs");
                if (test('(') || test(' ')) {
                    AllExpressions<T> result = parseValue();
                    return new Abs<>(result, type);
                }
                throw new ParsingException("Use abs in correct format");
            } else if (take('s')) {
                expect("quare");
                if (test ('(') || test(' ')) {
                    AllExpressions<T> result = parseValue();
                    return new Square<>(result, type);
                }
                throw new ParsingException("Use square in correct format");
            }
            else if (take('-')) {
                if (between('0', '9')) {
                    StringBuilder sb = new StringBuilder();
                    sb.append('-');
                    takeDigits(sb);
                    try {

                        return new generic.Const<>(sb.toString(), type);
                    } catch (NumberFormatException e) {
                        throw new ParsingException("Number is too small");
                    }
                } else {
                    skipWhitespace();
                    if (test('\0')) {
                        throw new ParsingException("No expression after minus on position " + getPosition());
                    }
                    AllExpressions<T> result = parseValue();
                    return new generic.UnaryMinus<>(result, type);
                }
            } else if (between('0', '9')) {
                StringBuilder sb = new StringBuilder();
                takeDigits(sb);
                try {
                    return new generic.Const<>(sb.toString(), type);
                } catch (NumberFormatException e) {
                    throw new ParsingException("Number is too big");
                }
            } else if (take('x')) {
                return new generic.Variable<>("x");
            } else if (take('y')) {
                return new generic.Variable<>("y");
            } else if (take('z')) {
                return new generic.Variable<>("z");
            } else if (take('(')) {
                skipWhitespace();
                if (test(')')) {
                    throw new ParsingException("No arguments in brackets");
                }
                AllExpressions<T> result = parseExpression();
                expect(')');
                return result;
            } else {
                throw new ParsingException("No such symbol on position " + getPosition());
            }
        }

        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9') ) {
                sb.append(take());
            }
        }
        private void skipWhitespace() {
            while (isItWhiteSpase()){
                take();
            }
        }
        private void checkExceptionsinExpression() throws ParsingException {
            if(test('*') || test('/') || test('+')){
                throw new ParsingException ("No second argument in operation"+" on position "+ super.getPosition());
            } else if (test(')')) {
                throw new ParsingException ("No second argument in operation"+" on position "+ super.getPosition());
            } else if (test('\0')) {
                throw new ParsingException ("No second argument in operation"+" in the end of source ");
            }
        }
        private void checkExceptionsinExpressions() throws ParsingException {
            if (test(')')){
                throw new ParsingException ("No open brackets");
            } else if (between('0','9')) {
                throw new ParsingException("Spaces in numbers");
            }
        }

    }
}