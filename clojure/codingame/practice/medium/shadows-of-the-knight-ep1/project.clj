(defproject shadows-of-the-knight-ep1 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot shadows-of-the-knight-ep1.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
