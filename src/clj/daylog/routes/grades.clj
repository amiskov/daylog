(ns daylog.routes.home
  (:require
   [daylog.layout :as layout]
   [daylog.db.core :as db]
   [clojure.java.io :as io]
   [daylog.middleware :as middleware]
   [ring.util.http-response :as response]
   [struct.core :as st]))

(defn grades-page [request]
  (layout/render request
                 "grades.html"
                 {:entries (db/get-entries)
                  :deeds (db/get-deeds)
                  :grades (db/get-grades)}))

(defn save-grade! [{:keys [params]}]
  (db/save-grade! params)
  (response/found "/"))

(defn grades-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/grades" {:get grades-page}]
   ["/grades" {:post save-grade!}]])

