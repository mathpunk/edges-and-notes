(ns edges-and-notes.data.neo
  (:require [clojurewerkz.neocons.rest :as rest]
            [clojurewerkz.neocons.rest.nodes :as nodes]
            [clojurewerkz.neocons.rest.relationships :as edges]
            [clojurewerkz.neocons.rest.cypher :as cy]
            [edges-and-notes.data.mongo :as mongo]))


(def notes (mongo/retrieve-notes))

(take 10 notes)

(let [conn  (rest/connect "http://localhost:7474/db/data/")]
  (nodes/create-index conn "an-index"))





; =======================================================================================
; SAMPLE CYPHER QUERIES (not even a little idempotent)
; =======================================================================================
; Here is how to find all Amy's friends via Cypher:

(let [amy   (nodes/create conn {:username "amy"})
      bob   (nodes/create conn {:username "bob"})
      _     (edges/create conn amy bob :friend {:source "college"})
      result   (cy/tquery conn "START person=node({sid}) MATCH person-[:friend]->friend RETURN friend" {:sid (:id amy)})]
  result)

; And here is how to get back usernames and ages of multiple people using Cypher:

(let [conn  (rest/connect "http://localhost:7474/db/data/")
      amy   (nodes/create conn {:username "amy" :age 27})
      bob   (nodes/create conn {:username "bob" :age 28})
      _     (edges/create conn amy bob :friend {:source "college"})
      result   (cy/tquery conn "START x = node({ids}) RETURN x.username, x.age" {:ids (map :id [amy bob])})]
  result)
