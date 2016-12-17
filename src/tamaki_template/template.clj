(ns tamaki-template.template
  (:require [tamaki-core.config :as config]
            [tamaki.post.post :as tpost]
            [hiccup.page :as hpage]
            [compojure.route :as route]
            [compojure.core :refer [GET defroutes]]
            [clojure.string :as string]
            [robert.hooke :as hooke]
            )
  (:import (java.text SimpleDateFormat)
           (java.util Locale)))

(defroutes handler
           (route/files "/" {:root "resources"})
           (route/not-found "Page not found"))
