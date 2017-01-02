(ns tamaki-template.template
  (:require [tamaki-core.config :as config]
            [hiccup.page :as hpage]
            [compojure.route :as route]
            [compojure.core :as ccore]
            [clojure.string :as string]
            [clojure.tools.logging :as log]
            [hiccup.page :as hpage]))

(defmacro inner-routes [config & routes]
  (let [context (gensym)]
    `(let [~context (:context ~config)]
       (if (some? ~context)
         (do (if-not (string/starts-with? ~context "/") (log/warn "context must start with /"))
             (ccore/context ~context [] ~@routes))
         (ccore/routes ~@routes)))))

(def handler
  (let [config (config/load-config)]
    (inner-routes config (route/files "/" {:root (:build config)}) (route/not-found "Page not found"))))

(defn- with-context
  ([prefix link] (str prefix "/" link)))

(defn single-page [doc config]
  (println config)
  (println "!!!!!")
  (println doc)
  (let [site-title (:title config)
        title (-> doc :metadata :title)
        link (-> doc :metadata :link)
        body  (:body doc)]
    (hpage/html5
      {:lang "en"}
      [:head
       [:meta {:charset "utf-8"}]
       [:title site-title]
       (hpage/include-css "/css/normalize.css")
       (hpage/include-css "/css/skeleton.css")
       (hpage/include-css "/css/common.css")
       (hpage/include-css "/css/page.css")]
      [:body
       [:div {:class "container"}
        [:div {:class "row"}
         [:header {:class "hd"}
          [:h2 {:class "title"} site-title]]
         [:nav {:class "navbar"}
          [:ul
           [:li [:a {:href link} "About"]]
           [:li "Archive"]]]
         [:article {:class "post"}
          [:header [:h1 {:class "heading"} title]]
          [:div body]]
         [:footer {:class "footer"}
          "Copyright &copy; Tamaki. All Rights Reserved."]]]])))
