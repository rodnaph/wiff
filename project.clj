
(defproject wiff "0.0.1"
  :description "Web diff viewer"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.1"]
                 [ring/ring-jetty-adapter "1.1.2"]
                 [ring/ring-devel "1.1.3"]
                 [jiff "0.0.2"]
                 [hiccup "1.0.1"]]
  :plugins [[lein-ring "0.7.2"]]
  :main wiff.core)

