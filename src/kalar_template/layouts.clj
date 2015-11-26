(ns kalar-demo.layouts
  [:require [hiccup.core :as hcore]
            [hiccup.page :as hpage]])

(defn a [sitename]
  (hpage/html5 [:span sitename])
  )



(defn nmsp [] (map #(.getName %) (all-ns)))
(filter #(re-seq #"kalar-demo.+" (str %)) (nmsp))