(defproject tamaki-template "0.1.7-SNAPSHOT"
  :description "a demo for tamaki"
  :url "https://github.com/satokazuma/tamaki-template"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/mit-license.php"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [hiccup "1.0.5"]
                 [clj-yaml "0.4.0"]
                 [compojure "1.4.0"]
                 [tamaki "0.1.8-SNAPSHOT"]]
  :plugins [[lein-ring "0.9.7"]
            [kalar "0.1.1-SNAPSHOT"]]
  :ring {:handler tamaki-core.server/handler
         :init tamaki-core.server/init
         :auto-reload? true})

