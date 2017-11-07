(defproject coders-strike-back "0.1.0-SNAPSHOT"
  :description "CodinGame Coder Strike Back (CSB) Competition"
  :url "https://www.codingame.com/ide/puzzle/coders-strike-back"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot coders-strike-back.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
