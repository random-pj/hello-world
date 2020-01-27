; (ns clj.core
;   (:gen-class)
;   (:require [io.pedestal.http :as http]
;             [io.pedestal.http.route :as route]
;             [io.pedestal.http.body-params :as body-params]
;             [io.pedestal.http.route.definition :refer [defroutes]]))

; (defn hello-world [req]
;   {:status 200 :body (str "Hello world!!")})

; (defroutes routes [[["/" {:get hello-world}]]])

; (def service
;   {:env                 :prod
;    ::http/routes        routes
;    ::http/type          :jetty
;    ::http/port          8080})


; (defn -main
;   "The entry-point for 'lein run'"
;   [& args]
;   (-> service
;       http/create-server
;       http/start))

(ns clj.core
  (:require [org.httpkit.server :as httpkit])
  (:gen-class))


(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "hello world!"})

(defn -main [& args]
  (httpkit/run-server app {:port 8080}))
