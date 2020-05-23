(ns clojure-test-1.collections
  (:require [clojure.string :as string :refer [split join]])
  )
;sequence is a collection in java (its just a data structure, like arraylist or hashmap or list)
;--sequences----------------- eg first, second, rest, can be done on list, vectors, hm, sets
(= 3 (first '(3 2 1)))
(= [20 30 40] (rest [10 20 30 40]))                         ;rest omits the first element

(:a {:a 10, :b 20, :c 30})                                  ;prints 10
((hash-map :a 10, :b 20, :c 30) :b)                         ;prints 20
(conj {:a 1} {:b 2} [:c 3])

;-------------- multiplies every thing by 10------------------------
(def myhm {:A 5  :B 10})

(defn transform-num
  [coll]                                                    ;input hm
  (reduce (fn [ncoll [k v]]                                 ;inner reduce function arg inputing our hm
            (assoc ncoll k (* 10 v)))                       ;multiply all the keys by 10
          {} coll))                                         ;output hm

(transform-num myhm)

;-------------- added "rd" to the end of everything-------------
(def newhm {:car "fo"  :poker "ca"})

(defn transform-word [coll]                                 ;input hm
  (reduce (fn [ncoll [k v]]                                 ;inner reduce function arg inputing our hm
            (assoc ncoll k (str v "rd")))                   ;multiply all the keys by 10
          {} coll))                                         ;output hm

(transform-word newhm)

;----- conj-- place an item at the front of the list or back of the vector

(conj '(2 3 4) 1)                                           ;list conj goes to front
(conj [1 2 3] 4)                                            ;vec conj add to back
(= #{1 2 3 4}(conj #{1 4 3} 2))                            ;sets.. has the hashtag in front of hm
;(conj {:a 1 :b 2} :c 3)                                     ;hashmap

;----- sets--------------------------------------------------
(set '(:a :a :b :c :c :c :c :d :d))
(clojure.set/union #{:a :b :c} #{:b :c :d :e})
(def s #{:a :b :c :d})
(conj s :e)

;-------------take/drop-------------------------------------

(take 3 [1 2 3 4 5 6 7 8 9 10])
; => (1 2 3)

(drop 3 [1 2 3 4 5 6 7 8 9 10])
; => (4 5 6 7 8 9 10)

;---------sort by count -------------------------------

(sort-by count ["aaa" "c" "bb"])

;----------take/drop while----------------------------------------

(take-while #(< (:month %) 3) [{:month 2} {:month 3}])
(drop-while #(< (:month %) 3) [{:month 2} {:month 3}])

;------filter-----------------------------------------
(filter #(< (:human %) 5) [{:human 2} {:human 5}])

;--------------------------some-----------------------
(some #(> (:critter %) 3) [{:critter 5} {:critter 6}])
;-------- nth------------------------------------------------
; returns second to last element
(defn secondToLast [x] (nth x (- (count x) 2)) )
(secondToLast [1 2 3])

(-(count '(1 2 4 6)))                                       ;return negative 4
(nth '(1 2 4 6)2)                                           ;returns [2] of list

;------- gets and element by index
(defn getNth [coll n]
  ((apply comp (cons first (repeat n rest))) coll))

(getNth '(4 5 6 7) 2)

;------- get list length
; reduce will apply plus to everything in the list, map will turn each item in list to 1
(reduce + (map (fn [x] 1)[1 2 3 5]))

(defn list-length [m]
  (reduce + (map (fn [m] 1)m)))

(list-length [1 2 3 5 6])

;-------- reverse--------- returns last element---------------
(comp (first (reverse [1 2 3 4 5])))

;----- reverse a list
(reduce #(cons %2 %1) '() '(1 2 3))
(reduce #(cons %2 %1) [] [4 5 6])

;------------ remove duplicates from this list----------------

(defn removeDup [s]
  (let [f*
        (fn [acc s*]
          (if (not= (last acc) s*)
            (conj acc s*)
            acc))]
    (reduce f* [] s)))

(removeDup [1 1 2 3 3 2 2 3])

;---------- duplicate every element in a list------------------
-
(defn dupElement [s]
  (let [temp
        (fn [x y]
          (conj x y y))]
    (reduce temp [] s)))
(dupElement [1 2 3])                                        ;[1 1 2 2 3 3]

(defn testa [x y]
  (conj x y y))
(testa [] [1 2 3])                                          ;[[1 2 3] [1 2 3]]

;----------- create a list of integers in a range---------------
(defn intRange [s e]
  (loop [x s acc []]
    (if (< x e)
      (recur (inc x) (conj acc x))
      acc)))

(intRange -1 2)

;-------- returns the largest number in a set-----------------

(defn getLargest [& s]
  (reduce (fn [acc s*] (if (> s* acc) s* acc)) (first s) (rest s)))

(getLargest 1 2 6 5)

;------- merges vectors and alternates---------------------
(#(mapcat vector %1 %2) [1 2 3] [4 5 6])


;---- normalizes mixed container types---------------------
(defn flatten [c]
  (let [f* (fn f [acc x]
             (if (coll? x) (reduce f acc (seq x)) (conj acc x))
             ;(list acc)
             )]
    (reduce f* [] c)
    ))
(flatten '((1 2) 3 [4 [5 6]]))


;---- return only odd numbers--------

(defn oddNum [x]
  (filter #(not= 0 (rem %1 2)) x))

(oddNum #{1 2 3 4 5})
;shorthand
;(filter #(not= 0 (rem %1 2)) #{1 2 3 4 5})

;------------  list functions-------------

; This will get the first 2 items in a list and assign them to fname lname
(defn listArg [coll]
  (let [[fname lname] coll]
    (prn fname) (prn lname)))

(listArg ["craig" "topham"])

;----------------------------------------

(def mangle
  (fn [string]
    (string/join "-" (string/reverse string))))

(mangle "kayrumba")

(defn demangle [mangled-string]
  (string/reverse (string/replace mangled-string "-" "" )))

(demangle (mangle "kayruma"))

;---------------------------------------

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])

(map :real identities)

;------------Threading macros------------
;The parameter after -> will get plugged in as the first arg for everything below it
(-> {:a 1}
    (assoc :b 2)                                            ;{:a 1 :b 2}
    (assoc :c 3)                                            ;{:a 1 :b 2 :c 3}
    keys)

; Take the value and substitute into the last arg of the function calls
(->> [1 2 3]
     (map inc)
     (map #(+ 2 %)))

; Take the value and substitute into the first or last arg of the function calls
(as-> [:foo :bar] $
      (map name $)
      (first $)
      (string/upper-case $))