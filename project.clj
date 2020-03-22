(defproject cloinche "0.1.0-SNAPSHOT"
  :description "Clojure coinche game, multiplayer, online, with GUI"
  :url "http://not.yet.sorry"
  :license {:name "Apache Licence 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :main ^:skip-aot cloinche.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
