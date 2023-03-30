class AbstractOperation {
    #args
    #op
    #operation
    #length_op
    constructor(op, operation, ...args){
        this.#args = [...args].splice(-operation.length)
        this.#op = op
        this.#operation = operation
        this.#length_op = operation.length
    }
    evaluate(x, y, z){
        return this.#operation(...this.#args.map(fn => fn.evaluate(x, y, z)));
    }
    toString(){
        return this.#args.map(fn => fn.toString()).join(" ") + " " + this.#op
    }
    get length_op(){
        return this.#length_op;
    }
}
class Add extends AbstractOperation {
    constructor(...args){
        super("+", (a, b) => a + b, ...args)
    }
}
class Subtract extends AbstractOperation {
    constructor(...args){
        super("-", (a, b) => a - b, ...args)
    }
}
class Multiply extends AbstractOperation {
    constructor(...args){
        super("*", (a, b) => a * b, ...args)
        
    }
}
class Divide extends AbstractOperation {
    constructor(...args){
        super("/", (a, b) => a / b, ...args)
    }
}
class Negate extends AbstractOperation {
    constructor(...args){
        super("negate", a => -a, ...args)
    }
}
class ArcTan extends AbstractOperation {
    constructor(...args){
        super("atan", a => Math.atan(a), ...args)
    }	
}
class ArcTan2 extends AbstractOperation {
    constructor(...args){
        super("atan2", (a, b) => Math.atan2(a, b), ...args)
    }	
}
class Const {
    #value
    constructor(arg) {
        this.#value = parseFloat(arg) 
    }
    evaluate(){
        return this.#value;
    }
    toString(){
        return this.#value.toString();
    }
}
class Variable {
    #arg
    constructor(arg) {
        this.#arg = arg
    }
    evaluate(...args){
        return args[this.#arg.charCodeAt(0) - 'x'.charCodeAt(0)]
    }
    toString(){
        return this.#arg.toString();
    }
}

const VARCONST = { 
    'x': new Variable('x'), 
    'y': new Variable('y'), 
    'z': new Variable('z'),
    }
    
const OPS = {
  '+': Add,
  '-': Subtract,
  '*': Multiply,
  '/': Divide,
  'negate': Negate,
  'atan': ArcTan,
  'atan2': ArcTan2
};


const parse = expression => {
  const stack = [];
  expression.trim().split(/\s+/).forEach(token => {
    if (token in OPS) {
        const op = new OPS[token](...stack.slice(-2, stack.length))
        stack.splice(-op.length_op)
        stack.push(op)
    } else if (token in VARCONST) {
      stack.push(VARCONST[token]);
    } else {
      stack.push(new Const(token));
    }
  });
  return stack[0];
}


