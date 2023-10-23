;;;;;;;;;;;;;;;;;;;;;;;; ex 10 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(load-file "proto.clj")
(load-file "parser.clj")

(defn func [f] (fn [& args] (fn [vars] (apply f (map #(% vars) args)))))
(def add (func +))
(def subtract (func -))
(def multiply (func *))
(defn myDivide [arg1 arg2]
  (/ (double arg1) (double arg2)))
(def divide (func myDivide))
(def negate subtract)
(def arcTan (func #(Math/atan %)))
(def arcTan2 (func #(Math/atan2 %1 %2)))
(defn constant [arg] (constantly arg))
(defn variable [arg] (fn [in_map] (get in_map arg)))

(def OPS {'+      add
          '-      subtract
          '*      multiply
          '/      divide
          'negate negate
          'atan   arcTan
          'atan2  arcTan2})




(defn parse [Mapp expr const variabls]
  (letfn [(parseToken [token]
            (cond
              (number? token) (const token)
              (list? token) (apply (Mapp (first token)) (map parseToken (rest token)))
              (symbol? token) (variabls (str token))))]
    (parseToken (read-string expr))))
(defn parseFunction [expr] (parse OPS expr constant variable))

;;;;;;;;;;;;;;;;;;;;;;; ex 11 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def _x (field :x))
(def _var (field :var))
(def _op (field :op))
(def _symb (field :symb))
(def _args (field :args))
(def evaluate (method :evaluate))
(def toString (method :toString))
(def toStringPostfix (method :toStringPostfix))

(def AbstractOperationPrototype
  {:evaluate        (fn [this vars] (apply (_op this) (mapv #(evaluate % vars) (_args this))))
   :toString        (fn [this] (str "(" (_symb this) (reduce str (mapv #(str " " %) (mapv toString (_args this)))) ")"))
   :toStringPostfix (fn [this] (str "(" (reduce str (mapv #(str % " ") (mapv toStringPostfix (_args this)))) (_symb this) ")"))})


(defn AbstractOperation [this symb op]
  (assoc this
    :symb symb
    :op op))
(def _AbstractOperation (constructor AbstractOperation AbstractOperationPrototype))


(defn Operation [symb op]
  (constructor
    (fn [this & args] (assoc this :args args))
    (_AbstractOperation symb op)))


(def Add
  (Operation "+" +))


(def Subtract
  (Operation "-" -))


(def Negate
  (Operation "negate" -))


(def Multiply
  (Operation "*" *))


(def Divide
  (Operation "/" myDivide))

(def Sinh
  (Operation "sinh" #(Math/sinh %)))

(def Cosh
  (Operation "cosh" #(Math/cosh %)))
(def UPow
  (Operation "**" #(Math/exp %)))

(def ULog
  (Operation "//" #(Math/log %)))

(def Constant
  (constructor
    (fn [this x]
      (assoc this :x x))
    {:evaluate        (fn [my_const vars] (_x my_const))
     :toString        (fn [my_const] (str (_x my_const)))
     :toStringPostfix (fn [my_const] (toString my_const))}))


(def Variable
  (constructor
    (fn [this var]
      (assoc this
        :var (str var)
        :fir_char (str (Character/toLowerCase (first var)))))
    {:evaluate        (fn [my_var vars] (get vars (:fir_char my_var)))
     :toString        (fn [my_var] (_var my_var))
     :toStringPostfix (fn [my_var] (toString my_var))}))



(def objectMap
  {'+       Add,
   '-       Subtract,
   '*       Multiply,
   '/       Divide
   'negate  Negate,
   'sinh    Sinh,
   'cosh    Cosh,
   '**      UPow,
   'log     ULog,
   "//"     'log,
   "**"     '**
   "+"      '+
   "-"      '-
   "/"      '/
   "*"      '*
   "negate" 'negate})

(defn parseObject [expr] (parse objectMap expr Constant Variable))


;;;;;;;;;;;;;;;;;;;;;;;;;;;; ex 12;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn +string [s] (+str (apply +seq (mapv (comp +char str) (char-array s)))))
(def *all-chars (mapv char (range 32 128)))
(defn *filter [f] (+char (apply str (filter f *all-chars))))
(def *ws (+ignore (+star (*filter #(Character/isWhitespace %)))))
(def *op (+char "+-/*"))
(def *unaryop (+or (+string "negate") (+string "**") (+string "//")))
(defn is? [chars] (+opt (+char chars)))
(def *int (+plus (*filter #(Character/isDigit %))))
(def *constant (+map Constant (+map #(Double/parseDouble %) (+str (+seqf #(flatten %&) (is? "-") *int (is? ".") (+opt *int))))))
(def *var (+char "xyzXYZ"))
(def *variable (+map Variable (+str (+plus *var))))
(def *operator-binary (+map (comp objectMap str) *op))
(def *operator-unary (+map (comp objectMap str) *unaryop))

(declare *parser-suffix)
(def *expression (+or (+seqf #(do ((objectMap (nth %& 2)) (nth %& 0) (nth %& 1))) *ws
                             (+ignore (+char "(")) *ws
                             (delay *parser-suffix) *ws
                             (delay *parser-suffix) *ws
                             *operator-binary *ws
                             (+ignore (+char ")")) *ws)
                      (+seqf #(do ((objectMap (nth %& 1)) (nth %& 0))) *ws
                             (+ignore (+char "(")) *ws
                             (delay *parser-suffix) *ws
                             *operator-unary *ws
                             (+ignore (+char ")")) *ws)))
(def *parser-suffix (+seqn 0 *ws (+or *variable *constant *expression) *ws))
(defn parseObjectPostfix [expr] ((+parser *parser-suffix) expr))

