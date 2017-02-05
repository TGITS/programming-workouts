(defproject perfect-number-app "0.1.0-SNAPSHOT"
  :description "A small app, done as an exercise to print the perfect number between 0 and another positive integer"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot perfect-number-app.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
