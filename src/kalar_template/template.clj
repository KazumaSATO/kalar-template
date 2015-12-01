(ns kalar-template.template
  (:require [kalar-plugins.templates.hiccup :as hp]
            [hiccup.page :as hpage]))

(hp/def-template
  "index.html"
  (hpage/html5
    [:head
     [:title "Home | Compojure Docs"]
     (hpage/include-js "/js/jquery-2.1.4.min.js")
     (hpage/include-js "/js/bootstrap.min.js")
     (hpage/include-css "/css/bootstrap.min.css")
     (hpage/include-css "/css/kalar.css")]
    [:body
     [:nav {:class "navbar navbar-default navbar-fixed-top"}
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
        [:a {:class "navbar-brand" :href "#"} "Project Name"]]
       [:div {:id "navbar" :class "navbar-collapse collapse"}
        [:ul {:class "nav navbar-nav"}
         (for [e '("Action" "About")] [:li [:a {:href "#"} e]])]]]]
     [:div {:class "container"}
      [:div {:class "row"}
       [:div {:class "col-xs-12 col-sm-6 col-md-8"} "bar"]
       [:div {:class "col-xs-6 col-md-4"} "foo" ]
       ]]
     ]))
