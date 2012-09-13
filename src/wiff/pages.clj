
(ns wiff.pages
  (:use hiccup.core
        hiccup.form
        jiff.vcs)
  (:require jiff.svn
            (hiccup [page :as page])
            [clojure.string :as string]))

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

(defn- line-type [line]
  (condp = (first line)
    \+ :pre.add
    \- :pre.remove
    :pre))

(defn- html-escape [line]
  (-> line
      (string/replace #"<" "&lt;")
      (string/replace #">" "&gt;")))

(defn- to-line [line]
  [(line-type line) (html-escape line)])

(defn- to-file-diff [file]
  [:div
    [:h3 (:path file)]
    [:div.code
      (map to-line (:lines file))]])

(defn- diff-view [{:keys [url1 url2]}]
  (let [files (jiff-seq {:vcs :svn
                         :from url1
                         :to url2})]
    (if (and url1 url2)
        [:div.row
          [:div.span12
            (map to-file-diff files)]])))

;; Public
;; ------

(defn index [{:keys [params]}]
  (html
    [:head
      [:title "Wiff!"]
      (page/include-css "/assets/bootstrap/css/bootstrap.min.css")
      (page/include-css "/assets/css/default.css")]
    [:body
      [:div.container
        [:div.row
          [:div.span12
            [:h1 "Wiff Web Differ"]]]
        [:div.row
          [:div.span12
            (url-form params)]]
       (diff-view params)]]))

