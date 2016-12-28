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

(defmacro inner-routes [config & routes]
  (let [context (gensym)]
    `(let [~context (:context ~config)]
       (if (some? ~context)
         (ccore/context ~context [] ~@routes)
         (ccore/routes ~@routes)))))

(def handler
  (let [config (config/load-config)]
    (inner-routes config (route/files "/" {:root (:build config)}) (route/not-found "Page not found"))))


