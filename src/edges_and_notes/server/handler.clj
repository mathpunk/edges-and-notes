(ns edges-and-notes.server.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.data.json :as json]))

(defn bad-alchemy [data]
  (str
  "<html>
    <head>
      <link rel=\"stylesheet\" href=\"http://cdn.graphalchemist.com/alchemy.min.css\">
      <script type=\"text/javascript\" src=\"http://cdn.graphalchemist.com/alchemy.min.js\"></script>
    </head>
    <body>
      <div id=\"alchemy\" class=\"alchemy\"></div>
  <script>
    var some_data = "

   data
   ;; it's a string of json
   ;; wtf
   ;; don't forget the semicolon
   ;; and the escaped quotes
   ;; ohgod
   ";
   alchemy.begin({\"dataSource\": some_data})
</script>

  </body>
</html>" ))


(defn alchemy-page [data]
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

(def test-data
     {:nodes
      [ {:id 1 :caption "wowow"} {:id 2 :caption "zowie"} {:id 3 :caption "whatWHAT"}]
      :edges
      [ {:source 1 :target 2 :caption "edgez"}
         {:source 1 :target 3 :caption "another edgez"} ]})

(defroutes app-routes
  (GET "/" [] "oh hai i'm teh internet")
  (GET "/data" [] (alchemy-page test-data))
  (route/resources "/")                          ;; These two routes came with the new Compojure project.
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
