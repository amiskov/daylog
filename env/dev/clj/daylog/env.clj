(ns daylog.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [daylog.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[daylog started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[daylog has shut down successfully]=-"))
   :middleware wrap-dev})
