(defproject tamaki-template "0.1.8.2"
  :description "a demo for tamaki"
  :url "https://github.com/satokazuma/tamaki-template"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/mit-license.php"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [hiccup "1.0.5"]
                 [clj-yaml "0.4.0"]
                 [compojure "1.4.0"]
                 [tamaki "0.2.1.1"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-tamaki "0.1.2.1"]]
  :ring {:handler tamaki-core.server/handler
         :init tamaki-core.server/init
         :auto-reload? true})

