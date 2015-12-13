(defproject kalar-template "0.1.2"
  :description "a demo for kalar"
  :url "https://github.com/KazumaSATO/kalar-template"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/mit-license.php"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [hiccup "1.0.5"]
                 [clj-yaml "0.4.0"]
                 [compojure "1.4.0"]
                 [kalar-core "0.1.2"]
                 [kalar-plugins "0.1.2"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler kalar-core.server/app
         :init kalar-core.server/init
         :auto-reload? true})

