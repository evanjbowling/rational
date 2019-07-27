(ns com.evanjbowling.rational)

(defn ^:private rational-seq*
  [rs]
  (let [r (first rs)
        [n d] (cond (integer? r) [r 1]
                    :else [(numerator r) (denominator r)])
        s (+' n d)]
    (lazy-cat [r]
              (rational-seq* (concat (rest rs)
                                     [(/ n s) (/ s d)])))))

(defn rational-seq
  []
  (rational-seq* [1]))

