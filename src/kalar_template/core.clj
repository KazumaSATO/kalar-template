(ns kalar-template.core
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.util.response :refer [redirect]]
            [ring.middleware.resource :refer [wrap-resource]]
            ))



(defroutes handler
  (GET ":prefix{.*}/" [prefix] (redirect (str prefix "/index.html")))
  (route/resources "/" {:root "_site"})
  (route/not-found "Page not found"))
