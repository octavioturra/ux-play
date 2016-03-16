(ns ux-play.routes.home
  (:require [compojure.core :refer :all]
            [ux-play.views.layout :as layout]
            [ring.util.response :refer [response]]
            [ux-play.models.db :as db]))

;; (defn home []
;;   (layout/common [:h1 "Hello World!"]))


(defn $$ [o k] (get-in o k))

(defn $# [o k]
  (let [value ($$ o k)]
  (if (some? value)
    value
    (throw (Exception. "Required field")))))

(defn get-users [] (db/all "users"))

(defn home []
  (response (get-users)))

(defn user [request]
  (response (db/create
              "users"
              {
                :name ($# request [:body  :name])
                :email ($# request [:body  :email])
                :password ($# request [:body  :password])
                :state 100
              }
            )))

(defroutes home-routes
  (GET "/" [] (home))
  (POST "/" request (user request)))
