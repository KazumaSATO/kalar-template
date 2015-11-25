(ns kalar-demo.core
  [:use [clojure.java.io :only [file]]]
  [:require [clj-yaml.core :as yaml]])

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(def config {:sitename "hoge"})

(defn load-project [project]
  (let [possible-config-dest (map #(file % "config.yml") (:resource-paths project))
        config (first (filter #(.exists %) possible-config-dest))]
    (println (yaml/parse-string (slurp (.getAbsolutePath config))))))

