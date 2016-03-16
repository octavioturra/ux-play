(ns ux-play.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.middleware.json :as json]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ux-play.routes.home :refer [home-routes]]))

(defn init []
  (println "ux-play is starting"))

(defn destroy []
  (println "ux-play is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes home-routes app-routes)
      (handler/site)
      (json/wrap-json-body {:keywords? true})
      (json/wrap-json-response)
      (wrap-base-url)))
