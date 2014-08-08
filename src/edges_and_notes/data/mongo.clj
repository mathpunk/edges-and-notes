(ns edges-and-notes.db.core
    (:require [monger.core :as mg]
              [monger.collection :as mc]
              [monger.operators :refer :all]))

(let [uri "mongodb://feuille/livre"]
  (mg/connect-via-uri! uri))

;; =============================================================================
;; My data collections
;; =============================================================================
; db livre

; collections
;   agents
;   annotations     ; markings for cards
;   articles        ; threw a pm wiki at it or something?
;   cards           ; pictures of cards
;   evernotes       ; notes migrated from evernote, lacking dates
;   fs.chunks       ; internal?
;   fs.files        ; internal?
;   notebookPages   ; pictures of notebook pages
;   system.indexes  ; internal?


;; =====================================================================
;; NOTES
;; =====================================================================

(def ^:dynamic *coll* "evernotes")
(def ^:dynamic *field* (partial assoc {} "tags"))

(mc/find-maps *coll* (*field*reference. "fucked"))

(defn find-maps []
  (mc/find-maps *coll* (*field* searching)))

(find-maps "fucked")

(defn get-some-marked-note [term]
  (mc/find-one-as-map "evernotes" {:tags term :notebook (or "morgue" "morgue archive")}))

(:_id (get-some-marked-note "networks"))
(get-some-marked-note "networks")


