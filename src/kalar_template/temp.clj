(ns kalar-demo.temp
  [:require [kalar-protocol.core :as proto]])

(deftype Temp []
  proto/KalarPlugin
  (kalar-eval [config] (println "hogehoge")))

(def e "fugafuga")
