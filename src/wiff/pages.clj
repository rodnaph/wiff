
(ns wiff.pages
  (:use hiccup.core
        hiccup.form)
  (:require (hiccup [page :as page])))

(defn- url-row [text id value]
  [:div.control-group
    [:label.control-label text]
    [:div.controls
      (text-field {:value value} id)]])

(defn- url-form [{:keys [url1 url2]}]
  (form-to {:class "form-horizontal"} [:get "/"]
    (url-row "URL 1" "url1" url1)
    (url-row "URL 2" "url2" url2)
    (submit-button {:class "btn btn-primary"} "Compare")))

;; Public
;; ------

(defn index [{:keys [params]}]
  (html
    [:head
      [:title "Wiff!"]
      (page/include-css "/assets/bootstrap/css/bootstrap.min.css")]
    [:body
      [:div.container
        [:div.row
          [:div.span12
            [:h1 "Wiff Web Differ"]]]
        [:div.row
          [:div.span12
            (url-form params)]]]]))

