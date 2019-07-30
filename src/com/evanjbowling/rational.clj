(ns com.evanjbowling.rational)

(defn ^:private nth-rational*
  "Returns the nth rational number (1-based) as a
  two element vector [numerator denominator] by
  navigating the Calkin-Wilf tree."
  [n]
  (if (< n 2)
    [1 1]
    (let [[pn pd] (nth-rational* (/ (cond-> n (odd? n) dec) 2))
          ps (+' pn pd)]
      (cond
        (odd? n) [ps pd]
        :else    [pn ps]))))

(defn nth-rational
  "Returns the rational number at the index
  specified (0-based). Throws for non-integer
  and non-positive indices."
  [index]
  (when (or (not (integer? index)) (< index 0))
    (throw (IllegalArgumentException. "index is not an integer >= 0")))
  (let [[rn rd] (nth-rational* (inc index))]
    (/ rn rd)))

(defn rational-seq
  "Returns infinite lazy sequence of rational numbers.
  First form returns all positive rational numbers.
  Second form starts at the specified index (0-based)."
  ([] (rational-seq 0))
  ([n]
   (lazy-seq
    (let [[rn rd] (nth-rational* (inc n))]
      (cons (/ rn rd) (rational-seq (inc n)))))))

