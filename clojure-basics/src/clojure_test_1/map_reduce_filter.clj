(ns clojure-test-1.map-reduce-filter)

(def my-list [ 2 5 9 14])
;---------- MAPS---------------------------------------
;does something to every e in a list
; map applied the function to the entire list, returns a new list
(map #(+ % 5) '(1 2 3))                                     ;anon function, input 1 2 3 as args

;----- another map
(defn sq [x]
  (* x x))

(map sq my-list)

;----odd one
(map vector [:a :b] [:c :d])

;-------- FILTER---------------------------------------
;filters out stuff want or dont want
(filter #(> % 5) '(3 4 5 6 7))
(filter #(< (:human %) 5) [{:human 2} {:human 5}])

;-------- REDUCE---------------------------------------
;takes a list and reduces it to a single thing

(defn reducer [m]
  (let [reduced-list (reduce + m)]
    reduced-list))

(reducer [1 2 3])
;---- returns sum of sequence
(#(reduce + 0 %1) [2 4 5])
(#(reduce + 5 %1) [2 4 6])                                  ;add all items in list then adds 5 to that

;Another reduce

(defn print-reducer [current next-e]
  (println "current" current "next element" next-e)
  (+ current next-e))

(reduce print-reducer [ 1 2 3])

;another reduce
(reduce (fn [new-map [key val]]                             ;inner function with 2 args, a map and list with 2 values
          (assoc new-map key (inc val)))                    ;take the new map key and incremenet the value
        {}                                                  ;arg1
        {:max 30 :min 10})                                  ;arg2

; another reduce

(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {}
        {:human 4.1
         :critter 3.9})