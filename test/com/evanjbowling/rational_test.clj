(ns com.evanjbowling.rational-test
  (:require
   [clojure.test              :refer [deftest is are]]
   [com.evanjbowling.rational :as r]))

(deftest test-first-few-rational-values
  (is (= [1
          1/2 2
          1/3 3/2 2/3 3
          1/4 4/3 3/5 5/2 2/5 5/3 3/4 4
          1/5 5/4 4/7 7/3 3/8 8/5 5/7 7/2 2/7 7/5 5/8 8/3 3/7 7/4 4/5 5]
         (take 31 (r/rational-seq))
         (map r/nth-rational (range 31)))
      "validates values, ordering and 0-based index"))

(deftest test-no-duplicates
  (is (->> (take 100 (r/rational-seq))
           frequencies
           (map second)
           (every? #(= 1 %)))))

(deftest test-large-n
  (is (= 205/162
         (nth (r/rational-seq) 10000)
         (r/nth-rational 10000)))
  (is (= 100081852181957676623/97939718087223099718
         (r/nth-rational 10000000000000000000000000000000000000000000000))
      "can handle large n such that the denominator is larger than Long/MAX_VALUE"))

(deftest test-invalid-inputs
  (are [n] (thrown-with-msg?
            IllegalArgumentException
            #"^index is not an integer >= 0.*$"
            (r/nth-rational n))
    -1
    -5
    1M
    1.1M
    0.25
    nil
    "3"
    :k))

