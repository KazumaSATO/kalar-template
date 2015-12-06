(ns kalar-template.template
  (:require [kalar-plugins.templates.hiccup :as hp]
            [kalar-core.config :as kconfig]
            [hiccup.page :as hpage]))

(hp/def-template
  "foo/index.html"
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
      [:div {:class "site-header"} "title"]
      [:div {:class "row"}
       [:div {:class "col-xs-12 col-sm-6 col-md-8"} "bar"]
       [:div {:class "col-xs-6 col-md-4"} "foo" ]
       ]]
     ]))


(hp/def-templates
  "posts/:id/index.html"
  "resources/posts"
  (hpage/html5
    [:head]
    [:body "hoge"]))

(def ^{:private true} config (kconfig/read-config))

(hp/def-excerpts
  "index.html" "page:num.html" "resources/posts" 3
  (hpage/html5
    [:head
     [:title (:title config)]
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
      [:div {:class "site-header"} [:h1 {:class "text-center"} (:title config)]]
      [:div {:class "row"}
       [:div {:class "col-xs-12 col-sm-6 col-md-8"}
        (for [post (:posts page_)]
          [:article {:class "post"}
           [:header
            [:div {:class "row"}
             [:div {:class "col-xs-12 col-sm-9"}
              [:h2 (-> post :title first)]]
             [:div {:class "col-sm-3 post-date"}
              [:time {:class "block"} "NOVENVER 15, 2015"]
              [:span "category"]]]]
           [:p (:excerpt post)]
           [:footer
            [:div {:class "row"}
             [:div {:class "col-sm-12 col-md-4 col-ld-3"}
              [:a {:type "button" :class "btn btn-default btn-lg btn-block" :role "button"} "Continue Reading"]]
             [:div {:class "col-md-8 col-ld-9"}]
             ]]])
        [:nav [:ul {:class "pager"}
               (if (-> page_ :previous-page nil?)
                 [:li {:class "disabled"} [:a {:href "#"} "Previous"]]
                 [:li [:a {:href (-> page_ :previous-page)} "Previous"]])
               (if (-> page_ :next-page nil?)
                 [:li {:class "disabled"} [:a {:href "#"} "Next"]]
                 [:li [:a {:href (-> page_ :next-page)} "Next"]])]]]
       [:div {:class "col-xs-6 col-md-4"} "hoge"]
       ]]
     ]))

(hp/def-page
  "resources/pages"
  (hpage/html5 [:head]))