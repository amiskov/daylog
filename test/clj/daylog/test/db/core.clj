(ns daylog.test.db.core
  (:require
    [daylog.db.core :refer [*db*] :as db]
    [luminus-migrations.core :as migrations]
    [clojure.test :refer :all]
    [clojure.java.jdbc :as jdbc]
    [daylog.config :refer [env]]
    [mount.core :as mount]))

(use-fixtures :once
  (fn [f] (mount/start
           #'daylog.config/env
           #'daylog.db.core/*db*)
    (migrations/migrate ["migrate"] (select-keys env [:database-url])) (f)))
(deftest test-messages
  (jdbc/with-db-transaction [t-conn *db*]
    (jdbc/db-set-rollback-only! t-conn)
    (is (= 1 (db/save-deed!
              t-conn
              {:title "Test deed"}
              {:connection t-conn})))
    (is (= {:title "Test deed"}
           (-> (db/get-deeds t-conn {})
               (first)
               (select-keys [:title]))))))

