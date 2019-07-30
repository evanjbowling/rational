# rational
[![Build Status](https://travis-ci.org/evanjbowling/rational.svg?branch=master)](https://travis-ci.org/evanjbowling/rational)

Generate a sequence of all positive rational numbers.

## Install

## Use

There are two main functions: `nth-rational` and `rational-seq`.

### `nth-rational`

Returns nth rational (0-based) as ordered by a breadth-first navigation of the [Calkin-Wilf tree](https://en.wikipedia.org/wiki/Calkin%E2%80%93Wilf_tree): 1, 1/2, 2/1, 1/3, 3/2, 2/3, 3/1, ...

Examples:

__First__
```clojure
(nth-rational 0)
;;=> 1
```

__Second__
```clojure
(nth-rational 1)
;;=> 1/2
```

__Big Indices__
```clojure
(nth-rational 98254)
;;=> 179/40
(nth-rational 10000000000000000000000000000000000000000000000)
;;=> 100081852181957676623/97939718087223099718
```

### `rational-seq`

Generates a lazy sequence of rational numbers following the same
order as `nth-rational` with an optional starting index.

__First 100 Rationals__

Include them all:

```clojure
(take 100 (rational-seq))
```

Skip the first 30:

```clojure
(take 70 (rational-seq 30))
```

__First Rational w/ Denominator Larger Than Max Integer Value__
```clojure
(->> (rational-seq 10000000000000000000)
     (filter #(cond->> % (ratio? %) denominator :else (< Integer/MAX_VALUE)))
     (take 1))
;;=> 1035723213/3048158966
```

