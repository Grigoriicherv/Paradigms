const cnst = value => () => value;
const VARIABLES = ['x', 'y', 'z'];
const operation = (op) => (...args) => (...vars) => op(...args.map(fn => fn(...vars)));
const variable = name => (...args) => args[VARIABLES.indexOf(name)];
const add = operation((a, b) => a + b);
const subtract = operation((a, b) => a - b);
const multiply = operation((a, b) => a * b);
const divide = operation((a, b) => a / b);
const negate = operation((a) => -a);
const sinh = operation(Math.sinh);
const cosh = operation((a) => Math.cosh(a));
const one = cnst(1);
const two = cnst(2);
const VARCONST = { 'one': one, 'two': two, 'x': variable('x'), 'y': variable('y'), 'z': variable('z') }
const OPS = {
  '+': { func: add, length: 2},
  '-': { func: subtract, length: 2 },
  '*': { func: multiply, length: 2 },
  '/': { func: divide, length: 2 },
  'negate': { func: negate, length: 1 },
  'sinh': { func: sinh, length: 1 },
  'cosh': { func: cosh, length: 1 }
};
const parse = expression => {
  const stack = [];
  expression.trim().split(/\s+/).forEach(token => {
    if (token in OPS) {
      stack.push(OPS[token].func(...stack.splice(-OPS[token].length)));
      console.log(OPS[token].length)
    } else if (token in VARCONST) {
      stack.push(VARCONST[token]);
    } else {
      stack.push(cnst(parseFloat(token)));
    }
  });
  return stack[0];
};
const testExpression = parse('x x * x 2 * - 1 +');
for (let i = 0; i < 11; i++) console.log(testExpression(i));

