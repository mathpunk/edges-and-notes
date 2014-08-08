(ns edges-and-notes.db.migrate
  (:require [clojurewerkz.neocons.rest :as rest]
            [clojurewerkz.neocons.rest.nodes :as nodes]
            [clojurewerkz.neocons.rest.relationships :as edges]
            [clojurewerkz.neocons.rest.cypher :as cy]))

;; THE PROBLEM
;; =================================================================================
;; I have notes in mongo db.
;; I want notes in neo4j.
;; =================================================================================

; CREATING NOTES
; ==================================================================================
; retrieve maps from the evernote morgue notebooks
; take every map, and add it as a unique node in neo4j with type "morgue".
; except, the tags are not an attr -- they are something to make edges with.
; so for every note, pause to create edges to terms.
; well, what if there is no term? in creating an edge, its target should be
; the unique node for that terms.
; so, don't forget to create terms.

(defn note-create! [{:keys [title content tags url oid id]}]
  (let [conn  (rest/connect "http://localhost:7474/db/data/")
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
