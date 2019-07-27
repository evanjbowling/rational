(defproject com.evanjbowling/rational "0.1.0-SNAPSHOT"
  :description "Tiny library to generate rational sequences."
  :url "http://example.com/FIXME"
  :license {:name "The MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :repl-options {:init-ns com.evanjbowling.rational}
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-cljfmt "0.6.4"]]}})
