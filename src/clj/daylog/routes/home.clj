(ns daylog.routes.home
  (:require
   [daylog.layout :as layout]
   [daylog.db.core :as db]
   [clojure.java.io :as io]
   [daylog.middleware :as middleware]
   [ring.util.http-response :as response]
   [struct.core :as st]))

(defn home-page [request]
  (layout/render request
                 "home.html"
                 {:entries (db/get-entries)
                  :deeds (db/get-deeds)
                  :grades (db/get-grades)}))

(defn save-grade! [{:keys [params]}]
  (db/save-grade! params)
  (response/found "/"))

(defn save-deed! [{:keys [params]}]
  (db/save-deed! params)
  (response/found "/"))

(defn save-entry! [{:keys [params]}]
  (db/save-entry!
    ;; TODO: There is a better way to cast string param values to integers
   (merge params
          {:deed_id (Integer/parseInt (:deed_id params))
           :grade_id (Integer/parseInt (:grade_id params))}))
  (response/found "/"))

(merge {:deed_id "30" :grade_id "20"} {:deed_id 30 :grade_id 20})

(defn about-page [request]
  (layout/render request "about.html"))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/grades" {:post save-grade!}]
   ["/deeds" {:post save-deed!}]
   ["/entries" {:post save-entry!}]
   ["/about" {:get about-page}]])

