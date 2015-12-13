(ns kalar-template.template
  (:require [kalar-core.config :as kconfig]
            [kalar-plugins.templates.page :as kpage]
            [hiccup.page :as hpage])
  (:import (java.text SimpleDateFormat)
           (java.util Locale)))


(def ^{:private true} config (kconfig/read-config))

(def ^:private date-formatter (SimpleDateFormat. "MMMMM dd, yyyy" Locale/US))
(defn- format-date [date] (.format date-formatter date))

(def ^:private get-head
  [:head
   [:title (:title config)]
   (hpage/include-js "/js/jquery-2.1.4.min.js")
   (hpage/include-js "/js/bootstrap.min.js")
   (hpage/include-css "/css/bootstrap.min.css")
   (hpage/include-css "/css/kalar.css")])

(def ^:private nav
  [:nav {:class "navbar navbar-kalar navbar-fixed-top"}
   [:div {:class "container"}
    [:div {:class "navbar-header"}
     [:button {:type "button"
               :class "navbar-toggle collapsed"
               :data-toggle "collapse"
               :data-target "#navbar"
               :aria-expanded "false"
               :aria-controls "navbar"}
      (concat '([:span {:class "sr-only"} "Toggle navigation"])
              (repeat 3 [:span {:class "icon-bar"}]))]
     [:h1 [:a {:class "navbar-brand" :href "/index.html"} (:title config)]]]
    [:div {:id "navbar" :class "navbar-collapse collapse"}
     [:ul {:class "nav navbar-nav"}
      [:li [:a {:href "/about/index.html"} "About"]]
      ]]]])

(defn single-column-page [md]
  (hpage/html5
    get-head
    [:body
     nav
     [:div {:class "container main-contents"}
      [:article {:class "post"}
       [:header
        [:div {:class "row"}
         [:div {:class "col-xs-12 col-sm-9"}
          [:h2 (-> md :title first)]]
         [:div {:class "col-sm-3 post-date"}
          [:time {:class "block"} (-> md :date format-date)]
          [:span (-> md :category first)]]]]
       (:body md)]]]))

(defn get-recent-posts []
  (let [num 3
        recent-posts (kpage/load-recent-posts num)]
    (for [post recent-posts]
      [:article {:class "mini-post"}
       [:div {:class "mini-post-title"}  [:a {:href (-> post :url)}[:h4 (-> post :title first)]]]
       [:div (-> post :date format-date)]])))

(defn paginate-template [mds]
  (hpage/html5
    get-head
    [:body
     nav
     [:div {:class "container"}
      [:div {:class "row main-contents"}
       [:div {:class "col-xs-12 col-sm-12 col-md-8"}
        (for [post (:posts mds)]
          [:article {:class "post"}
           [:header
            [:div {:class "row"}
             [:div {:class "col-xs-12 col-sm-9 col-md-8 col-lg-9"}
              [:h2 (-> post :title first)]]
             [:div {:class "col-sm-3 col-sm-md-4 col-lg-3 post-date"}
              [:time {:class "block"} (-> post :date format-date)]
              [:span (-> post :category first)]]]]
           [:p (:excerpt post)]
           [:footer
            [:div {:class "row"}
             [:div {:class "col-sm-12 col-md-4 col-ld-3"}
              [:a {:type "button"
                   :class "btn btn-default btn-lg btn-block"
                   :role "button"
                   :href (:url post)}
               "Continue Reading"]]
             [:div {:class "col-md-8 col-ld-9"}]
             ]]])
        [:nav [:ul {:class "pager"}
               (if (-> mds :previous-page nil?)
                 [:li {:class "disabled"}
                  [:a {:href "#"} [:span {:class "glyphicon glyphicon-menu-left" :aria-hidden "true"}]]]
                 [:li [:a {:href (-> mds :previous-page)}
                       [:span {:class "glyphicon glyphicon-menu-left" :aria-hidden "true"}]]])
               (if (-> mds :next-page nil?)
                 [:li {:class "disabled"} [:a {:href "#"}
                                           [:span {:class "glyphicon glyphicon-menu-right" :aria-hidden "true"}]]]
                 [:li [:a {:href (-> mds :next-page)}
                       [:span {:class "glyphicon glyphicon-menu-right" :aria-hidden "true"}]]])]]]
       [:div {:class "col-sm-12 col-xs-12 col-md-4"}
        (get-recent-posts)]]]]))

(defn diary [md]
  (hpage/html5
    get-head
    [:body
     nav
     [:div {:class "container"}
      [:div {:class "row main-contents"}
       [:div {:class "col-xs-12 col-sm-12 col-md-8"}
        [:article {:class "post"}
         [:header
          [:div {:class "row"}
           [:div {:class "col-xs-12 col-sm-9 col-md-8 col-lg-9"}
            [:h2 (-> md :title first)]]
           [:div {:class "col-sm-3 col-sm-md-4 col-lg-3 post-date"}
            [:time {:class "block"} (-> md :date format-date)]
            [:span (-> md :category first)]]]]
         (:body md)
         ]
        [:nav [:ul {:class "pager"}
               (if (-> md :previous-page nil?)
                 [:li {:class "disabled"} [:a {:href "#"}
                                           [:span {:class "glyphicon glyphicon-menu-left" :aria-hidden "true"}]]]
                 [:li [:a {:href (-> md :previous-page)}
                       [:span {:class "glyphicon glyphicon-menu-left" :aria-hidden "true"}]
                       ]])
               (if (-> md :next-page nil?)
                 [:li {:class "disabled"} [:a {:href "#"}
                                           [:span {:class "glyphicon glyphicon-menu-right" :aria-hidden "true"}]]]
                 [:li [:a {:href (-> md :next-page)}
                       [:span {:class "glyphicon glyphicon-menu-right" :aria-hidden "true"}]]])]]]
       [:div {:class "col-sm-12 col-xs-12 col-md-4"}
        (get-recent-posts)]]]]))
