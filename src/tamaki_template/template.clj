(ns tamaki-template.template
  (:require [tamaki-core.config :as config]
            [hiccup.page :as hpage]
            [compojure.route :as route]
            [compojure.core :as ccore]
            [clojure.string :as string]
            [clojure.tools.logging :as log]
            [hiccup.page :as hpage])
  (:import (java.text SimpleDateFormat)
           (java.util Locale))
  )

(defmacro inner-routes [config & routes]
  (let [context (gensym)]
    `(let [~context (:context ~config)]
       (if (some? ~context)
         ; TODO
         (do (if-not (string/starts-with? ~context "/") (log/warn "context must start with /"))
             (ccore/context ~context [] ~@routes))
         (ccore/routes ~@routes)))))

(def handler
  (let [config (config/load-config)]
    (inner-routes config (route/files "/" {:root (:build config)}) (route/not-found "Page not found"))))

(def ^:private date-formatter (new SimpleDateFormat "MMMM d, yyyy" Locale/US))
(defn- to-datestr [date] (.format date-formatter date))


(defn- head [title css]
  [:head
       [:meta {:charset "utf-8"}]
       [:title title]
       (hpage/include-css "/css/normalize.css")
       (hpage/include-css "/css/skeleton.css")
       (hpage/include-css "/css/common.css")
       (hpage/include-css (str "/css/" css))])

(defn pagenate-nav [prev next]
  [:div {:class "pagenate"}
   [:ul
    (if (some? prev) [:li [:a {:href prev} "&lt;"]] [:li {:class "disable"} "&lt;"])
    (if (some? next) [:li [:a {:href next} "&gt;"]] [:li {:class "disable"} "&gt;"])]])

(defn post [doc config]
  (let [site-title (:title config)]
    (hpage/html5
      {:lang "en"}
      (head site-title "post.css")
      [:body
       [:div {:class "container"}
        [:div {:class "row"}
         [:header {:class "hd"}
          [:h2  {:class "title"}
           [:a {:href (:context config) :class "hoverless"} site-title]]]
         [:nav {:class "navbar"}
          [:ul
           [:li [:a {:href "/about/index.html" :class "hoverless"} "About"]]]]
         [:article {:class "post"}
          [:header
           [:h1 {:class "heading"} (-> doc :meta :title)]
           [:div {:class "date"} (-> doc :date to-datestr)]]
          [:div (:body doc)]]
         (pagenate-nav (:previous doc) (:next doc))]]])))

(defn pagenate [doc config]
  (let [site-title (:title config)]
    (hpage/html5
      {:lang "en"}
      (head site-title "posts.css")
      [:body
       [:div {:class "container"}
        [:div {:class "row"}
         [:header {:class "hd"}
          [:h1
           [:a {:href (:context config) :class "hoverless"} site-title]]]
         [:nav {:class "navbar"}
          [:ul
           [:li [:a {:href "/about/index.html"} "About"]]]]
         (for [post (:posts doc)]
           [:article {:class "post"}
            [:header
             [:h2 (-> post :meta :title)]
             [:div {:class "date"} (-> post :date to-datestr)]]
            [:div (:excerpt post)]
            [:footer [:a {:class "button" :href (:current post)} "Read more"]]])
         (pagenate-nav (:previous doc) (:next doc))
         [:footer {:class "footer"}
          "Copyright &copy; Tamaki. All Rights Reserved."]]]])))

(defn single-page [doc config]
  (let [site-title (:title config)
        title (-> doc :meta :title)
        link (-> doc :meta :link)
        body  (:body doc)]
    (hpage/html5
      {:lang "en"}
      (head site-title "page.css")
      [:body
       [:div {:class "container"}
        [:div {:class "row"}
         [:header {:class "hd"}
          [:h2 {:class "title"}
           [:a {:href (:context config) :class "hoverless"} site-title]]]
         [:nav {:class "navbar"}
          [:ul
           [:li [:a {:href link :class "hoverless"} "About"]]]]
         [:article {:class "post"}
          [:header [:h1 {:class "heading"} title]]
          [:div body]]
         [:footer {:class "footer"}
          "Copyright &copy; Tamaki. All Rights Reserved."]]]])))
