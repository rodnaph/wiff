
(ns wiff.core
  (:require [wiff.web :as web]
            [ring.adapter.jetty :as jetty]))

(defn -main []
  (jetty/run-jetty web/app {:port 5555}))

