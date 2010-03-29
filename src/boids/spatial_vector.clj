(ns boids.spatial-vector)

(defstruct spatial-vector :x :y)

(defn distance-between [p1 p2]
  (let [dx (- (:x p1) (:x p2))
	dy (- (:y p1) (:y p2))]
    (Math/sqrt (+ (* dx dx) (* dy dy)))))

(defn sv-sum 
  ([] (struct-map spatial-vector :x 0 :y 0))
  ([p1] p1)
  ([p1 p2] 
     (struct-map spatial-vector 
       :x (+ (:x p1) (:x p2)) 
       :y (+ (:y p1) (:y p2))))
  ([p1 p2 & more]
     (reduce sv-sum (sv-sum p1 p2) more)))

(defn sv-div [sv d]
  (struct-map spatial-vector :x (/ (:x sv) d) :y (/ (:y sv) d)))

(defn sv-mul
  ([m sv] (struct-map spatial-vector :x (* (:x sv) m) :y (* (:y sv) m))))

(defn sv-diff 
  ([] (struct-map spatial-vector :x 0 :y 0))
  ([p1] p1)
  ([p1 p2] 
     (struct-map spatial-vector 
       :x (- (:x p1) (:x p2)) 
       :y (- (:y p1) (:y p2))))
  ([p1 p2 & more]
     (reduce sv-diff (sv-diff p1 p2) more)))
