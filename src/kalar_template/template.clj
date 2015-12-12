(ns kalar-template.template
  (:require [kalar-core.config :as kconfig]
            [hiccup.page :as hpage]))


(def ^{:private true} config (kconfig/read-config))

(defn single-column-page [md]
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
      [:article {:class "post"}
       [:header
        [:div {:class "row"}
         [:div {:class "col-xs-12 col-sm-9"}
          [:h2 (-> md :title first)]]
         [:div {:class "col-sm-3 post-date"}
          [:time {:class "block"} "NOVENVER 15, 2015"]
          [:span "category"]]]]
       (:body md)]]]))

(defn paginate-template [mds]
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
        (for [post (:posts mds)]
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
       [:div {:class "col-xs-6 col-md-4"} "hoge"]]]]))

(defn diary [md]
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
        [:article {:class "post"}
         [:header
          [:div {:class "row"}
           [:div {:class "col-xs-12 col-sm-9"}
            [:h2 (-> md :title first)]]
           [:div {:class "col-sm-3 post-date"}
            [:time {:class "block"} "NOVENVER 15, 2015"]
            [:span "category"]]]]
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
       [:div {:class "col-xs-6 col-md-4"} "hoge"]]]]))
