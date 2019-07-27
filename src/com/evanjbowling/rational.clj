(ns com.evanjbowling.rational)

(defn ^:private nth-rat
  "Returns the nth rational number as a two element
  vector [numerator denominator] by navigating the
  Calkin-Wilf tree."
  [n]
  (if (= 1 n)
    [1 1]
    (if (odd? n)
      (let [[pn pd] (nth-rat (/ (dec n) 2))]
        [(+' pn pd) pd])
      (let [[pn pd] (nth-rat (/ n 2))]
        [pn (+' pn pd)]))))

(defn nth-rational
  "Returns the nth rational number."
  [n]
  ;; TODO: add additional input validation
  (let [[rn rd] (nth-rat n)]
    (/ rn rd)))

(defn ^:private rat-seq*
  [n]
  (lazy-seq
   (let [[rn rd] (nth-rat n)]
     (cons (/ rn rd) (rat-seq* (inc n))))))

(defn rational-seq
  "Returns infinite lazy sequence of all rational
  numbers as ordered in the Calkin-Wilf tree."
  [] (rat-seq* 1))

