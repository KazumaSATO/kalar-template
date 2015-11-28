(defproject kalar-template "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [hiccup "1.0.5"]
                 [clj-yaml "0.4.0"]
                 [compojure "1.4.0"]
                 [kalar-core "0.1.0-SNAPSHOT"]
                 [kalar-plugins "0.1.0-SNAPSHOT"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler kalar-core.server/handler
         :init kalar-core.server/init})

