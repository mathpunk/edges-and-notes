(ns edges-and-notes.test.handler
  (:require [clojure.test :refer :all]
            [edges-and-notes.server.handler :refer :all]
            [ring.mock.request :as mock]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "oh hai i'm teh internet"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
