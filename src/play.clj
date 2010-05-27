(def r (vec (partition 10 (range 1000))))

(def x 1)
(def y 2)
(def z (+ 1 (binding [x 3 y 3] (+ x y))))
(prn z)

;; See http://onclojure.com/2009/03/06/a-monad-tutorial-for-clojure-programmers-part-2/
(use 'clojure.contrib.monads)
(defn m-bind-first-try [sequence function]
  (map function sequence))

(m-bind-first-try (range 5) 
		  (fn [a] (m-bind-first-try (range a)
					    (fn [b] (* a b)))))

(defmonad first-try-m [m-bind m-bind-first-try])

(domonad first-try-m [a (range 5) b (range a)] (* a b))

;; The functional form of the first try 
(map (fn [a] (map (fn [b] (* a b)) (range a))) (range 5))


;; Here's the correct end result done without the monand form.  It just
;; applies concat to the outer function call.  It basically just calls
;; (apply concat ...) on the list provided by the first try 
(apply concat (map (fn [a] (map (fn [b] (* a b)) (range a))) (range 5)))

;; The functional form of the correct flat answer try
;; Note that concat has to be applied to both function bindings to simulate
;; the mbind form.  If you only had the outer concat without the list call
;; it would work just fine, but because you have to apply the mbind at each stage
;; of the computation, you have to undo the inner concat by turning the results
;; into Lists instead of Integers
(apply concat (map (fn [a] 
		     (apply concat (map (fn [b] (list (* a b)))
			  (range a))))
		   (range 5)))

;; presumably, this is the same as
(defn m-bind-second-try [sequence function]
  (apply concat (map function sequence)))

;; The manual form using the m-bind function
(m-bind-second-try (range 5)  
		   (fn [a]
		     (m-bind-second-try (range a)  
					(fn [b] (list (* a b))))))

;; Defining the monad	(list (* a b))))))
(defmonad times-for-m 
  [m-result #(list %)
   m-bind m-bind-second-try])

;; doin it and doin it and doin it right!
(domonad times-for-m [a (range 5) b (range a)] (* a b))

