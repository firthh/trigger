(defproject trigger "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [org.clojars.firthh/simple-queue "0.1.1-SNAPSHOT"]
                 [liberator "0.12.2"]
                 [ring/ring-json "0.3.1"]
                 [org.clojure/data.json "0.2.6"]
                 [environ "1.0.0"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler trigger.handler/app
         :init trigger.handler/init
         :destroy trigger.handler/destroy}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
