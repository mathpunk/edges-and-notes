;; WARNING: NOT REAL
;; ===============================================================================================
;; This exists to provide an example of how Luminus took markdown in the public directory and applied
;; layout and a html-ifier to it. This is relevant to my interests. Kellyn ought to have a directory she
;; can just git mv something to, and have it be made available on the site.



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
