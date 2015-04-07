(ns trigger.handler
  (:use [ring.middleware.json :only [wrap-json-response wrap-json-body]])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [simple-queue.core :as q]
            [clojure.data.json :as json]
            [environ.core :refer [env]]))

(defonce queue (atom {}))

(defn trigger [body]
  (q/publish @queue (json/write-str {:event "download-new-messages" :available-at (env :jive-uri)}))
  "published message")

(defroutes app-routes
  (POST "/trigger" req (trigger (:body req)))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults api-defaults)
      wrap-json-response
      (wrap-json-body {:keywords? true :bigdecimals? true})))

(defn destroy []
  (.stop @queue)
  (reset! queue {}))

(defn init []
  (reset! queue (q/create-queue "test" (env :queue-uri))))
