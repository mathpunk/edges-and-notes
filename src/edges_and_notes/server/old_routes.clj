(ns edges-and-notes.routes.home
  (:require [edges-and-notes.layout :as layout]
            [edges-and-notes.util :as util]
            [compojure.core :refer :all]
            [noir.response :refer [edn]]
            [clojure.pprint :refer [pprint]]))

(defn home-page []
      (layout/render
        "home.html" {:content (util/md->html "/md/docs.md")}))

(defn chapter-page [title]
      (layout/render
        "chapter.html" {:content (util/md->html (str "/md/" title ".md"))}))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/chapter/:title" [title] (chapter-page title)))
