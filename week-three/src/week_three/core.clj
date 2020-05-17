(ns week-three.core
  (:require [clojure.string :as string :refer [split]])
  )


(def reverse-concat
  ;this method will concat the string together and then reverse it
  (comp (partial apply str) reverse str))
(reverse-concat "hello" "there")

(defn functwo [x n]
  ;takes a collection and a int, this function will divide the collection
  ;the number of times if n. ex  [1 2 3 4] with n as 2 would be [1 2] [3 4]
  (let [temp (partition n n x)]
    (println "temp" temp)
    (for [i (range n)] (map #(nth %1 i) temp))))

(functwo [1 2 3 4] 2)

(defn functhree [coll n]
  ; this function takes a sequence and a int.
  ;ever iteration n will increase by 1 and then that number will be added to
  ;the next  index
  ; example args[[1 2] 2] would be [3 5]
  (map + coll (iterate inc n)))

(functhree [1 2 ] 2)