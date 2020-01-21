(ns clj.core
  (:gen-class)
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route.definition :refer [defroutes]]))


;   Running 10s test @ http://localhost:8080
; 10 connections
; ┌─────────┬──────┬──────┬───────┬──────┬─────────┬─────────┬──────────┐
; │ Stat    │ 2.5% │ 50%  │ 97.5% │ 99%  │ Avg     │ Stdev   │ Max      │
; ├─────────┼──────┼──────┼───────┼──────┼─────────┼─────────┼──────────┤
; │ Latency │ 0 ms │ 0 ms │ 1 ms  │ 4 ms │ 0.13 ms │ 0.84 ms │ 22.04 ms │
; └─────────┴──────┴──────┴───────┴──────┴─────────┴─────────┴──────────┘
; ┌───────────┬─────────┬─────────┬─────────┬─────────┬──────────┬─────────┬─────────┐
; │ Stat      │ 1%      │ 2.5%    │ 50%     │ 97.5%   │ Avg      │ Stdev   │ Min     │
; ├───────────┼─────────┼─────────┼─────────┼─────────┼──────────┼─────────┼─────────┤
; │ Req/Sec   │ 20159   │ 20159   │ 25103   │ 25519   │ 24652.37 │ 1457.32 │ 20145   │
; ├───────────┼─────────┼─────────┼─────────┼─────────┼──────────┼─────────┼─────────┤
; │ Bytes/Sec │ 9.51 MB │ 9.51 MB │ 11.9 MB │ 12.1 MB │ 11.6 MB  │ 690 kB  │ 9.51 MB │
; └───────────┴─────────┴─────────┴─────────┴─────────┴──────────┴─────────┴─────────┘
; Req/Bytes counts sampled once per second.
; 271k requests in 11.07s, 128 MB read

(defn hello-world [req]
  {:status 200 :body (str "Hello world!!")})

(defroutes routes [[["/" {:get hello-world}]]])

(def service
  {:env                 :prod
   ::http/routes        routes
   ::http/type          :jetty
   ::http/port          8080})


(defn -main
  "The entry-point for 'lein run'"
  [& args]
  (-> service
      http/create-server
      http/start))
