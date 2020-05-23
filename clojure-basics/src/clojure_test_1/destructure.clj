(ns clojure-test-1.destructure
  (:require [clojure.string :as string :refer [split join]]
  ))

;------Maps and destructuring----------------------------------
(def testMap {:english "hello there" :spanish "hola buenos dias" :nestedkey {:a/Z 1 :b/Y 2}})

;key words are functions that extract the value in a hm
(get testMap :english)                                      ;get the value of a key
(vals testMap)                                              ;gets all the values in a map
(keys testMap)                                              ;gets all the keys
(get-in testMap [:nestedkey :a/Z])                          ;get nested value
(testMap :nestedkey)                                        ;get value by keyword
(:nestedkey testMap)                                        ;get value by keyword

;destructuring----------
(defn destructuring [{:keys [english spanish]}]             ;destructuring, taking a map as a arg
  [english spanish])
(destructuring testMap)                                     ;returns values for keys given in list

; another destructure----
(defn destructure3 [{mBind :foo}]
  mBind)
(destructure3 {:foo 2 })                                    ;this way mBind is the value you can name it whatever

; another destructure-----
(defn destructure4 [{[a b] :foo}]
  (+ a b))
(destructure4 {:foo [1 2] })

; another destructure-----
(defn destructure5 [m]
  (let [mOne (get-in m [:nestedkey :a/Z])]
    (prn mOne))
  )
(destructure5 testMap)

;another destructure------
(defn destructure6 [{:keys [nestedkey]}]
  (val (first nestedkey)))
(destructure6 testMap)

;--------another destructure
(defn sq [x]
  (* x x))

(defn sq-list [[x y z]]
  [(sq x) (sq y) (sq z)])

(sq-list [1 2 3])
;non destructured--------
(defn destructure2 [m]
  [(:a m) (:b m) (:c m)])
(destructure2 {:a 1 :b 2 :c 3})                             ;[1 2 3]

