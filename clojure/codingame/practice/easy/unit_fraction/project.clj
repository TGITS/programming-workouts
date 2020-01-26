(defproject unit_fraction "0.1.0-SNAPSHOT"
  :description "CodinGame Community Puzzle 'Unit Fraction'"
  :url "https://www.codingame.com/ide/puzzle/unit-fractions"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :main ^:skip-aot Solution
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
