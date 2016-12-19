(ns tamaki-template.template
  (:require [tamaki-core.config :as config]
            [tamaki.post.post :as tpost]
            [hiccup.page :as hpage]
            [compojure.route :as route]
            [compojure.core :as ccore]
            [clojure.string :as string]
            [robert.hooke :as hooke]
            )
  (:import (java.text SimpleDateFormat)
           (java.util Locale)))

(def handler
  (let [config (config/load-config)]
    (ccore/routes
      (route/files "/" {:root (:build config)})
      (route/not-found "Page not found"))))

