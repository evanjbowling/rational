(ns com.evanjbowling.rational)

(defn ^:private nth-rat
  "Returns the nth rational number as a two element
  vector [numerator denominator] by navigating the
  Calkin-Wilf tree."
  [n]
  (if (< n 2)
    [1 1]
    (let [[pn pd] (nth-rat (/ (cond-> n (odd? n) dec) 2))
          ps (+' pn pd)]
      (cond
        (odd? n) [ps pd]
        :else    [pn ps]))))

(defn nth-rational
  "Returns the nth rational number."
  [n]
  (when (or (not (integer? n)) (< n 1))
    (throw (IllegalArgumentException. "n is not an integer > 0")))
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

