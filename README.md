# rational

Generate a sequence of all positive rational numbers.

## Install

## Use

There's one main function `rational-seq` that generates a lazy sequence of all rational numbers.

```clojure
(ns test
  (:require
    [com.evanjbowling.rational :as r]))

(defn print-some-rationals
  [n]
  (print (take n (r/rational-seq))))

(print-some-rationals 100)
```

