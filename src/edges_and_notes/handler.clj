(ns edges-and-notes.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]))

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
   "alchemy.begin({\"dataSource\": some_data})
</script>

  </body>
</html>" ))

(def bad-test-data
     "{ 
        \"nodes\": 
          [ { \"id\": 1 }, 
            { \"id\": 2 }, 
            { \"id\": 3 } ], 
        \"edges\": 
          [ { \"source\": 1, \"target\": 2 }, 
            { \"source\": 1, \"target\": 3, } ] 
     };")

(defroutes app-routes
  (GET "/wat" [] (bad-alchemy bad-test-data))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
