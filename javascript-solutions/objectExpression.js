//////////////////////////////////////////////////////////// EX 7
let OPS = {}
let VAR = {}
const AbstractExpression = {
    toString: function () {
        return this.value.toString();
    },
    prefix: function () {
        return this.value.toString();
    }
}

function AbstractOperation(operation, operator, length) {
    const op = function (...args) {
        this.evaluate = function (x, y, z) {
            return operation(...args.map(fn => fn.evaluate(x, y, z)));
        }
        this.toString = function () {
            return args.map(fn => fn.toString()).join(" ") + " " + operator
        }
        this.prefix = function () {
            return '(' + operator + " " + args.map(fn => fn.prefix()).join(" ") + ")"
        }
    };

    OPS[operator] = {func: op, length};
    return op;
}


function AbstractConst() {
    const Exp = function (value) {
        this.value = value
        this.evaluate = function (...xyz) {
            return value
        }

    }
    Exp.prototype = Object.create(AbstractExpression);
    return Exp
}

const Const = AbstractConst()

function Variable(value) {
    this.value = value
    this.evaluate = function (...xyz) { // :NOTE: in prototype
        return xyz[value.charCodeAt(0) - 'x'.charCodeAt(0)]
    }
}

Variable.prototype = Object.create(AbstractExpression);
VAR.x = 0
VAR.y = 1
VAR.z = 2

const Add = AbstractOperation(
    (a, b) => a + b, '+', 2
)
const Subtract = AbstractOperation(
    (a, b) => a - b, '-', 2
)
const Multiply = AbstractOperation(
    (a, b) => a * b, '*', 2
)
const Divide = AbstractOperation(
    (a, b) => a / b, '/', 2
)
const Negate = AbstractOperation(
    a => -a, 'negate', 1
)
const ArcTan = AbstractOperation(
    Math.atan, 'atan', 1
)
const ArcTan2 = AbstractOperation(
    Math.atan2, 'atan2', 2
)
const Sum = AbstractOperation(
    (...args) => args.reduce((total, current) => total + current, 0), 'sum', 'n'
)
const Avg = AbstractOperation(
    (...args) => args.reduce((total, current) => total + current, 0) / args.length, 'avg', 'n'
)


const parse = expression => {
    const stack = [];
    expression.trim().split(/\s+/).forEach(token => {
        if (token in OPS) {
            stack.push(new OPS[token].func(...stack.splice(-OPS[token].length)));
        } else if (token in VAR) {
            stack.push(new Variable(token));
        } else {
            stack.push(new Const(parseFloat(token)));
        }
    });
    return stack[0];
};


//////////////////////////////////////////////////////////// EX 8

const isNumeric = n => !isNaN(n);

const parsePrefix = expression => {
    if (expression.length === 0) {
        throw new Error("Empty input")
    }
    const tokens = expression.replace(/(\()|(\))/g, ' $& ').trim().split(/\s+/)
    return parseExpression(tokens)

}

function ParseError(message) {
    this.message = message;

}

ParseError.prototype = new Error();

function Source(expression) {
    const tokens = expression;
    let pos = -1;

    this.getNextToken = () => {
        pos++
        return tokens[pos]

    }
    this.getCurrentToken = () => {
        return tokens[pos];
    }
    this.getPosition = () => {
        return pos
    }
    this.testNextToken = () => {
        return tokens[pos + 1];
    }
    this.eof = () => {
        return pos === tokens.length - 1;
    }
}


function parseExpression(expression) {
    const source = new Source(expression)
    let ans = parseInsideExpression();
    if (!source.eof()) {
        throw new ParseError("Expected eof on position on position" + source.getPosition())
    }
    return ans;

    function parseInsideExpression() {
        const token = source.getNextToken();
        if (token === "(") {
            let operation = parseOperation();
            if (source.getNextToken() !== ")") {
                throw new ParseError("Expected closed brackets on position" + source.getPosition());
            }
            return operation;
        } else if (token in VAR) {
            return new Variable(token);
        } else if (isNumeric(token)) {
            return new Const(parseInt(token));
        }
        throw new ParseError("Not correcr token or not expected EOF on position" + source.getPosition());
    }

    function parseOperation() {
        const operator = parseOperator();
        const args = parseArgs();

        if (args.length !== operator.length && operator.length !== 'n') {
            throw new ParseError("Not correct number of args for operation")
        }

        return new operator.func(...args);
    }

    function parseOperator() {
        let operator = source.testNextToken();
        if (operator in OPS) {
            return OPS[source.getNextToken()];
        }
        throw new ParseError("Haven't operator or not in OPS on position" + source.getPosition())
    }

    function parseArgs() {
        const args = [];
        let token = source.testNextToken();
        while (!(token in OPS) && token !== ")") {
            args.push(parseInsideExpression());
            token = source.testNextToken();
        }
        return args;
    }
}

