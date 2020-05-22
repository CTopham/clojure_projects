(ns week-three.core-test
  (:require [clojure.test :refer :all]
            [week-three.core :refer :all]))


;;Craig Topham -  Week 1 Tests
(deftest reverse-concat-test
  (is (= "erehtolleh" (reverse-concat "hello" "there"))))

(deftest coll-div-test
  (is (= '((1 3) (2 4)) (coll-div [1 2 3 4] 2 ))))

(deftest inc-add-test
  (is (= '(3 5) (inc-add [1 2 ] 2))))