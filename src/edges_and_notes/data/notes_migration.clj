(ns edges-and-notes.data.notes-migration
  (:require [clojurewerkz.neocons.rest :as neo]
            [clojurewerkz.neocons.rest.nodes :as nodes]
            [clojurewerkz.neocons.rest.relationships :as edges]
            [monger.core :as mongo]
            [monger.collection :as mongo-coll]
            [monger.operators :refer :all]))


;; THE PROBLEM
;; =================================================================================
;; I have notes in mongo db.
;; I want notes in neo4j.
;; =================================================================================
;;
;; Plan:
;;  Retrieve every morgue/morgue archive note from Mongo
;;  Union all tags from Mongo notes.
;;  For each tag, make tag into a unique Neo node of type "term".
;;  For each note, make note into a unique Neo node of type "note",
;;    and for each term in :tags note, make unique Neo edge from note to term of type ;; "tagged".
;;  Did it work?



;; MONGO CONNECTION
;; ==============================================================================
;; feuille is an ssh tunnel on my machine to where the database is hosted
;; livre is the name of the database
(let [uri "mongodb://feuille/livre"]
  (mongo/connect-via-uri! uri))

;; COLLECTIONS: NOTES
;; ==============================================================================
;;
;; The first priority is retrieving the "evernotes" collection. Only the ones from the "morgue" and "morgue archive"
;; are wanted for this project. Let's leave out the "content" for now and concentrate on the graph structure that emerges
;; from the tags only; we can always get content from the Mongo DB later.

(mongo-coll/find-one-as-map "evernotes" {:notebook (or "morgue" "morgue archive")} ["_id" "tags" "title" "url"])

(defn retrieve-notes-with-content []
  "Retrieves every note from the evernotes collection, morgue/morgue archive notebook, with content. Resource intensive
  (at least a minute)."
  (mongo-coll/find-maps "evernotes" {:notebook (or "morgue" "morgue archive")}))

(defn retrieve-notes []
  "Retrieves every note from the evernotes collection, morgue/morgue archive notebook, excluding content for
  lighter use of resources (a few seconds)."
  (mongo-coll/find-maps "evernotes" {:notebook (or "morgue" "morgue archive")} ["_id" "tags" "title" "url"]))

(defn retrieve-content-by-oid [oid]
  "Given the _id from an evernote map, return the content from the livre database."
  ,,,)


;; NEO CONNECTION
;; =============================================================================
;; local

(let [conn (neo/connect "http://localhost:7474/db/data/")]
  (nodes/create-index conn "an-index"))

;; feuille
;; ?????????????????



;; THE TRANSFORMATION
;; =============================================================================

; SPIKE
; ==================================================================================
; Toying with the problem

(defn note-create! [{:keys [title content tags url oid id]}]
  (let [conn  (neo/connect "http://localhost:7474/db/data/")
        this-note (nodes/create conn {:title title :content content :url url})
        id (:id this-note)]
    this-note))

(def monkey1 {:id "one million" :tags ["farts" "monkeys" "art criticism"]
              :title "Critique of 'Monkey Farter'" :content "The fart comes from the monkey's butt which is also farting" :url "http://zefrank"})

(def monkey2 {:id "a bazillion" :tags ["copyright" "monkey" "photography" "art criticism"]
              :title "Monkey Takes Selfie" :content "C'mon give the monkey the copyright"})

(defn note-edges-create! [note]
  (doseq [term (:tags note)]
    (edges/create term note :type "tags")
    (edges/create note term :type "tagged")))

; ACTUAL CODE
; ==================================================================================
; Write some damn tests first!
