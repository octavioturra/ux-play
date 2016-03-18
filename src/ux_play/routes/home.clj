(ns ux-play.routes.home
  (:require [compojure.core :refer :all]
            [ux-play.views.layout :as layout]
            [ux-play.models.db :as db]
            [ux-play.utils.crypto :as crypto]
            [ring.util.response :refer [response]]))

;; (defn home []
;;   (layout/common [:h1 "Hello World!"]))


(defn $$ [o k] (get-in o k))

(defn $# [o k]
  (let [value ($$ o k)]
  (if (some? value)
    value
    (throw (Exception. "Required field")))))

(defn get-users [] (db/all "users"))

(defn initialize []
  (db/initialize))

(defn home []
  (response (get-users)))

(defn user [request]
  (let [name ($# request [:body  :name])]
  (response (db/create
              "users"
              {
                :name name
                :email ($# request [:body  :email])
                :password (crypto/pbkdf2 ($# request [:body  :password]) (subs name 4))
                :state 100
              }
            ))))

(defn test [request]
  (response {:headers   (get (:headers request) "connection")}))

(defroutes home-routes
  (GET "/" [] (home))
  (POST "/" request (user request))
  (GET "/initialize" [] (initialize))
  (GET "/test" request (test request)))
