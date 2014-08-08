(ns edges-and-notes.data.core
    (:require [monger.core :as mg]
              [monger.collection :as mc]
              [monger.operators :refer :all]))

;; livre is the name of the database
;; feuille is an ssh tunnel to where the database is hosted
(let [uri "mongodb://feuille/livre"]
  (mg/connect-via-uri! uri))

;; =============================================================================
;; My data collections
;; =============================================================================
; db livre

; collections
; INDEX CARDS
;   cards           ; pictures of cards
;   annotations     ; markings for cards
;   agents          ; useragents of those who marked cards
; NOTEBOOK SCANS
;   notebookPages   ; pictures of notebook pages
; NOTES
;   evernotes       ; notes migrated from evernote, lacking dates
;   articles        ; threw a pm wiki at it or something?

(let [coll "evernotes"
      term "fucked"]
  (mc/find-one-as-map coll {:tags term :notebook (or "morgue" "morgue archive")}))
