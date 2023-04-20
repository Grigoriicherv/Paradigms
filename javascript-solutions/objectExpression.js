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

function AbstractOperation(operation, operator) {
    const op = function (...args) {
    	this.args = args
        
    };
	
	op.prototype.evaluate = function (x, y, z) {
            return operation(...this.args.map(fn => fn.evaluate(x, y, z)));
        }
	op.prototype.toString = function () {
            return this.args.map(fn => fn.toString()).join(" ") + " " + operator
        }
	op.prototype.prefix = function () {
            return '(' + operator + " " + this.args.map(fn => fn.prefix()).join(" ") + ")"
        }
        op.prototype.length = operation.length
    OPS[operator] = op;
    return op;
}


function Const(value) {
    this.value = value;
    
};
Const.prototype = Object.create(AbstractExpression);
Const.prototype.evaluate = function (...xyz) {
        return this.value;
};


function Variable(value) {
    this.value = value;
    
};
Variable.prototype = Object.create(AbstractExpression);
Variable.prototype.evaluate = function (...xyz) {
        return xyz[this.value.charCodeAt(0) - 'x'.charCodeAt(0)];
};
VAR.x = 0
VAR.y = 1
VAR.z = 2

const Add = AbstractOperation(
    (a, b) => a + b, '+'
)
const Subtract = AbstractOperation(
    (a, b) => a - b, '-'
)
const Multiply = AbstractOperation(
    (a, b) => a * b, '*'
)
const Divide = AbstractOperation(
    (a, b) => a / b, '/'
)
const Negate = AbstractOperation(
    a => -a, 'negate'
)
const ArcTan = AbstractOperation(
    Math.atan, 'atan'
)
const ArcTan2 = AbstractOperation(
    Math.atan2, 'atan2'
)
const Sum = AbstractOperation(
    (...args) => args.reduce((total, current) => total + current, 0), 'sum'
)
const Avg = AbstractOperation(
    (...args) => args.reduce((total, current) => total + current, 0) / args.length, 'avg'
)


const parse = expression => {
    const stack = [];
    expression.trim().split(/\s+/).forEach(token => {
        if (token in OPS) {
            stack.push(new OPS[token](...stack.splice(-OPS[token].prototype.length)));
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
function tokenize(expression) {
  let tokens = [];
  let token = '';
  for (let i = 0; i < expression.length; i++) {
    if (expression[i] === '(' || expression[i] === ')') {
      if (token) {
        tokens.push(token);
        token = '';
      }
      tokens.push(expression[i]);
    } else if (/\s/.test(expression[i])) {
      if (token) {
        tokens.push(token);
        token = '';
      }
    } else {
      token += expression[i];
    }
  }
  if (token) {
    tokens.push(token);
  }
  return tokens;
}
const parsePrefix = expression => {
    if (expression.length === 0) {
        throw new Error("Empty input")
    }
    const tokens = tokenize(expression)
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

        if (args.length !== operator.prototype.length && operator.prototype.length !== 0) {
            throw new ParseError("Not correct number of args for operation")
        }

        return new operator(...args);
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
        while (token !== ")") {
            args.push(parseInsideExpression());
            token = source.testNextToken();
        }
        return args;
    }
}
