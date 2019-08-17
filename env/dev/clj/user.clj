(ns user
  "Userspace functions you can run by default in your local REPL."
  (:require
    [daylog.config :refer [env]]
    [clojure.spec.alpha :as s]
    [expound.alpha :as expound]
    [mount.core :as mount]
    [daylog.core :refer [start-app]]
    [daylog.db.core]
    [conman.core :as conman]
    [luminus-migrations.core :as migrations]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(add-tap (bound-fn* clojure.pprint/pprint))

(defn start 
  "Starts application.
  You'll usually want to run this on startup."
  []
  (mount/start-without #'daylog.core/repl-server))

(defn stop 
  "Stops application."
  []
  (mount/stop-except #'daylog.core/repl-server))

(defn restart 
  "Restarts application."
  []
  (stop)
  (start))

(defn restart-db 
  "Restarts database."
  []
  (mount/stop #'daylog.db.core/*db*)
  (mount/start #'daylog.db.core/*db*)
  (binding [*ns* 'daylog.db.core]
    (conman/bind-connection daylog.db.core/*db* "sql/queries.sql")))

(defn reset-db 
  "Resets database."
  []
  (migrations/migrate ["reset"] (select-keys env [:database-url])))

(defn migrate 
  "Migrates database up for all outstanding migrations."
  []
  (migrations/migrate ["migrate"] (select-keys env [:database-url])))

(defn rollback 
  "Rollback latest database migration."
  []
  (migrations/migrate ["rollback"] (select-keys env [:database-url])))

(defn create-migration 
  "Create a new up and down migration file with a generated timestamp and `name`."
  [name]
  (migrations/create name (select-keys env [:database-url])))


