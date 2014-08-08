(ns edges-and-notes.db.neo
  (:require [clojurewerkz.neocons.rest :as rest]
            [clojurewerkz.neocons.rest.nodes :as nodes]
            [clojurewerkz.neocons.rest.relationships :as edges]
            [clojurewerkz.neocons.rest.cypher :as cy]))

; Here is how to find all Amy's friends via Cypher:

(let [conn  (rest/connect "http://localhost:7474/db/data/")
      amy   (nodes/create conn {:username "amy"})
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
