(ns edges-and-notes.db.users
    (:require [monger.core :as mg]
              [monger.collection :as mc]
              [monger.operators :refer :all]))

(let [uri "mongodb://feuille/livre"]
  (mg/connect-via-uri! uri))

(defn create-user [user]
  (mc/insert "users" user))

(defn update-user [id first-name last-name email]
  (mc/update "users" {:id id}
             {$set {:first_name first-name
                    :last_name last-name
                    :email email}}))

(defn get-user [id]
  (mc/find-one-as-map "users" {:id id}))

;; ================================================================
;; User Agents
;; ================================================================

; MongoDB has some user agents, which maybe we could use to populate a user's page with cards they annotated, or whatever.
