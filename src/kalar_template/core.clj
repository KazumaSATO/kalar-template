(ns kalar-template.core
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.util.response :refer [redirect]]
            [ring.middleware.resource :refer [wrap-resource]]
            ))




