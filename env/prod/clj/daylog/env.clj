(ns daylog.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[daylog started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[daylog has shut down successfully]=-"))
   :middleware identity})
