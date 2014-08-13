(defproject edges-and-notes "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                [org.clojure/data.json "0.2.5"]
                 [compojure "1.1.8"]
                 [com.novemberain/monger "1.7.0"]
                 [markdown-clj "0.9.47"]
                 [enlive "1.1.5"]
                 [clojurewerkz/neocons "3.0.0"]]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler edges-and-notes.server.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
