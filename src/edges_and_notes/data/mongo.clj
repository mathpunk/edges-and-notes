(ns edges-and-notes.data.mongo
    (:require [monger.core :as mg]
              [monger.collection :as mc]
              [monger.operators :refer :all]))

;; CONNECTION
;; ==============================================================================
;; feuille is an ssh tunnel on my machine to where the database is hosted
;; livre is the name of the database
(let [uri "mongodb://feuille/livre"]
  (mg/connect-via-uri! uri))


;; COLLECTIONS: NOTES
;; ==============================================================================
;
; The first priority is retrieving the "evernotes" collection. Only the ones from the "morgue" and "morgue archive"
; are wanted for this project. Let's leave out the "content" for now and concentrate on the graph structure that emerges
; from the tags only; we can always get content from the Mongo DB later.

(mc/find-one-as-map "evernotes" {:notebook (or "morgue" "morgue archive")} ["_id" "tags" "title" "url"])

(defn retrieve-notes-with-content []
  "Retrieves every note from the evernotes collection, morgue/morgue archive notebook, with content. Resource intensive
  (at least a minute)."
  (mc/find-maps "evernotes" {:notebook (or "morgue" "morgue archive")}))

(defn retrieve-notes []
  "Retrieves every note from the evernotes collection, morgue/morgue archive notebook, excluding content for
  lighter use of resources (a few seconds)."
  (mc/find-maps "evernotes" {:notebook (or "morgue" "morgue archive")} ["_id" "tags" "title" "url"]))

(defn retrieve-content-by-oid [oid]
  "Given the _id from an evernote map, return the content from the livre database."
  ,,,)

(take 10 (retrieve-notes))




; ==========================================================================================
; OTHER COLLECTIONS: CARDS, NOTEBOOKS, and WIKI
; ==========================================================================================
; Of possible interest later.
;
; INDEX CARDS
;   cards           ; pictures of cards
;   annotations     ; markings for cards
;   agents          ; useragents of those who marked cards
;
; NOTEBOOK SCANS
;   notebookPages   ; pictures of notebook pages
;   See also Google Docs spreadsheet for pages that are useable
;
; ARCHIVED WIKI
; There's also an "articles" collection, from when I chucked a Punk Mathematics wiki at the database. Not of great interest
; but could be fun for vocabulary stats or a mess of Burroughs text or something?
;
;   articles        ; threw a pm wiki at it or something?
