(ns edges-and-notes.server.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.data.json :as json]))

(def test-data
     {:nodes
      [ {:id 1 :caption "wowow"} {:id 2 :caption "zowie"} {:id 3 :caption "whatWHAT"}]
      :edges
      [ {:source 1 :target 2 :caption "edgez"}
         {:source 1 :target 3 :caption "another edgez"} ]})

(defn alchemy-full-page [data]
  (str
  "<html>
    <head>
      <link rel=\"stylesheet\" href=\"http://cdn.graphalchemist.com/alchemy.min.css\">
      <script type=\"text/javascript\" src=\"http://cdn.graphalchemist.com/alchemy.min.js\"></script>
    </head>
    <body>
      <div id=\"alchemy\" class=\"alchemy\"></div>
      <script>alchemy.begin({\"dataSource\":"    (json/write-str data)     "})</script>
    </body>
  </html>"))

(defroutes app-routes
  (GET "/" [] "oh hai i'm teh internet")
  (GET "/test/full-page" [] (alchemy-full-page test-data))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
