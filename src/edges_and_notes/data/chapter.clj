(ns edges-and-nodes.data.chapter
  (:require [clojurewerkz.neocons.rest :as nr]
            [clojurewerkz.neocons.rest.nodes :as nn]
            [clojurewerkz.neocons.rest.relationships :as nrl]
            [clojurewerkz.neocons.rest.cypher :as cy]))

;; A wee test chapter
(def test-chapter {:title "How To Test"
                   :content "This chapter has words"
                   :keywords ["writing" "language" "words" "networks" #"taxonom(y|ie)s?"]
                   :sections []})

;; ================================================================
;; Initialize: Traverse from Chapter Node
;; ================================================================

;; conn
;;   be connected

;; cypher
;;   start with chapter node
;;   go outward to terms
;;   go outward from those to notes

;; return
;;   yay it's data


;; ===============================================================
;; CYPHER EXAMPLE
;; ===============================================================

(let [conn  (nr/connect "http://localhost:7474/db/data/")
      amy   (nn/create conn {:username "amy"})
      bob   (nn/create conn {:username "bob"})
      _     (nrl/create conn amy bob :friend {:source "college"})
      res   (cy/tquery conn "START person=node({sid}) MATCH person-[:friend]->friend RETURN friend" {:sid (:id amy)})]
  res)

;; And here is how to get back usernames and ages of multiple people using Cypher:

(let [conn  (nr/connect "http://localhost:7474/db/data/")
      amy   (nn/create conn {:username "amy" :age 27})
      bob   (nn/create conn {:username "bob" :age 28})
      _     (nrl/create conn amy bob :friend {:source "college"})
      res   (cy/tquery conn "START x = node({ids}) RETURN x.username, x.age" {:ids (map :id [amy bob])})]
  res)
