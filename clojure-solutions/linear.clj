(defn func [f] (fn [& args] (apply mapv f args)))

(def v+ (func +))
(def v- (func -))
(def v* (func *))
(def vd (func /))

(defn scalar [& args]
  (apply + (apply v* args)))

(defn vect [arg1 arg2]
  (letfn [(Coord [param1, param2]
            (- (* (nth arg1 param1) (nth arg2 param2)) (* (nth arg1 param2) (nth arg2 param1))))]
    (vector (Coord 1, 2) (Coord 2, 0) (Coord 0, 1))))

(def m+ (func v+))
(def m- (func v-))
(def m* (func v*))
(def md (func vd))

(defn v*s [v s]
  (mapv #(* % s) v))

(defn m*s [m s]
  (mapv #(v*s % s) m))

(defn m*v [m v]
  (mapv #(scalar % v) m))

(defn transpose [m]
  (apply mapv vector m))

(defn m*m [arg1 arg2]
  (mapv (partial m*v (transpose arg2)) arg1))

(defn s+ [v1 v2]
  (letfn [(op [x y]
            (if (vector? x)
              (s+ x y)
              (+ x y)))]
    (if (vector? v1)
      (mapv op v1 v2)
      (op v1 v2))))



(defn s- [v1 v2]
  (letfn [(op [x y]
            (if (vector? x)
              (s- x y)
              (- x y)))]
    (if (vector? v1)
      (mapv op v1 v2)
      (op v1 v2))))


(defn s* [v1 v2]
  (letfn [(op [x y]
            (if (vector? x)
              (s* x y)
              (* x y)))]
    (if (vector? v1)
      (mapv op v1 v2)
      (op v1 v2))))

(defn sd [v1 v2]
  (letfn [(op [x y]
            (if (vector? x)
              (sd x y)
              (/ x y)))]
    (if (vector? v1)
      (mapv op v1 v2)
      (op v1 v2))))





