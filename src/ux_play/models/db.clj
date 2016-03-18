(ns ux-play.models.db
  (:require [clojure.java.jdbc :as sql]))

(def db
  {:subprotocol "postgresql"
   :subname "//192.168.99.100:32768/VaraRoxa"
   :user "VaraRoxa"
   :password "OvoCaixaLeitePeixeMoc19820313CasaLatenciaFrauen"})

(def spec )

(defn initialize []
  (sql/db-do-commands db (sql/create-table-ddl :users
      [:id "BIGSERIAL PRIMARY KEY"]
      [:name "varchar(512)"]
      [:email "varchar(512)"]
      [:password "varchar(128)"]
      [:state :int]
    )))

(defn all [table]
  (sql/query db [(format "select * from %s order by id desc" table)]))

(defn create [table data]
  (sql/insert! db table data))
