
(ns wiff.web
  (:use compojure.core
          (ring.middleware [reload :only [wrap-reload]]))
  (:require [wiff.pages :as pages]
            (compojure [handler :as handler]
                       [route :as route])))

(defroutes main-routes
  (GET "/" [] pages/index)
  (route/resources "/assets")
  (route/not-found "Not found..."))

(def app (-> main-routes
             (handler/site)
             (wrap-reload)))

