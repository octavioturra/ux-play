(ns ux-play.views.layout
  (:require [hiccup.page :refer [html5 include-css]]))

(defn common [& body]
  (html5
    [:head
     [:title "Welcome to ux-play"]
     (include-css "/css/screen.css")]
    [:body body]))
