(ns clojure-test-1.scratch
  (:require [clojure.string :as string :refer [split join]]
  ))


(def people
  {:alex   {:name "alex" :mustache true :hair-color "black" :hair-type "long" :gender "male"}
   :alfred {:name "alfred" :mustache true :hair-color "black" :hair-type "long" :gender "male"}
   }
  )

(defn generate-target []
  (let [target (rand-nth (map conj (keys people)))          ;gets a key from map of people
        target-info (get people target)                     ;get the info
        ]
    target-info
    ))

(generate-target)

(defn target-info [s]
  (get people s)
  )

(target-info :alex)

;were going to get keys here and pass it to dynamic-answers
(def keycodes
  {:brown-hair [98 114 111 119 110 32 104 97 105 114 13]
   :blue-eyes [1 2 3]
   })

;this returns the attribute from the key code map
(defn decode [hm word-array]
  (let [m hm
        attribute (keys (select-keys m (for [[k v] m :when (= v word-array)] k)))
        ]
    (nth attribute 0)
    ))

(decode keycodes [1 2 3])

;-----------decode with words translated

(defn decoder [s]
  (let [hair (string/includes? s "hair")
        eyes (string/includes? s "eyes")
        mustache (string/includes? s "mustache")
        ]
    (cond
      (= hair true) {:attribute "hair" :full-input s}
      (= eyes true) :eyes
      (= mustache true) :mustache
      :else :unknown)
    )
  )
(decoder "brown hair")

(get {:test "did this work"} :test)
;----------interpret

(defmulti interpret :attribute)

(defmethod interpret "hair" [m]
  (cond
    (string/includes? (get m :full-input) "brown") (assoc m :hair-color "brown")
    (string/includes? (get m :full-input) "red") (assoc m :hair-color "red")
    (string/includes? (get m :full-input) "blonde") (assoc m :hair-color "blonde")
    (string/includes? (get m :full-input) "black") (assoc m :hair-color "black")
    (string/includes? (get m :full-input) "bald") (assoc m :hair-color "bald")
    (string/includes? (get m :full-input) "short") (assoc m :hair-type "short")
    (string/includes? (get m :full-input) "long") (assoc m :hair-type "long")
    (string/includes? (get m :full-input) "curly") (assoc m :hair-type "curly")
    (string/includes? (get m :full-input) "straight") (assoc m :hair-type "straight")
    )
  )

(interpret (decoder "Does he have short hair?"))

;------------Answer
(def interpret-map {:attribute "hair" :full-input "bob" :hair-type "short"})
(def targ-map {:name "alfred" :mustache true :hair-color "black" :hair-type "long" :gender "male"})

;Takes the interpreted map and the target map
;checks to see if the user guess the correct person
;checks to see if the question the user ask is right or wrong
;always returns a text response that we set as our
(defn Dynamic-Answers [m targ]
  (if (string/includes? (get m :full-input) (get targ :name))
    (#(str "Yes! You guessed it! the person is " %1) targ)
    (cond
      (contains? m :hair-type) (if (#(= (get m :hair-type) %) (get targ :hair-type))
                                 (#(str "Yes! the persons hair is " %1) (get targ :hair-type))
                                 (#(str "No, the persons hair is NOT " %1) (get targ :hair-type)))
      :else "unknown command")
    )
  )
  (Dynamic-Answers interpret-map targ-map)

;#(str "Yes, the person has" % ) (get m :hair-type)


;(if (#(= (get m :hair-type) %) (get targ :hair-type)) (println "yes the hair is short"))


(#(str "hello, " %1) "!")
;------------ translate keycodes into words
(def key-mapper
  {         :113 "q"
            :119	"w"
            :101	"e"
            :114	"r"
            :116	"t"
            :121	"y"
            :117	"u"
            :105	"i"
            :111	"o"
            :112	"p"
            :97	"a"
            :115	"s"
            :100	"d"
            :102	"f"
            :103	"g"
            :104	"h"
            :106	"j"
            :107	"k"
            :108	"l"
            :122	"z"
            :120	"x"
            :99	"c"
            :118	"v"
            :98	"b"
            :110	"n"
            :109	"m"
            :32 " "
            :13 ""
            }

  )

(defn testfunc [coll]
  (let [kws (map #(keyword (str %))coll)]                   ;transform letter to keyword
    (vals (select-keys key-mapper kws))                     ;gets a list back
    )
  )
(testfunc [104 101 104 13])

(defn testfuncc [coll]
  (let [kws (map #(keyword (str %))coll)]                   ;transform letter to keyword
    (for [x kws
          :let [y (get key-mapper x)]]
      y)
    )
  )
(testfuncc [104 101 104 13])

(defn testfunccc [coll]
  (let [kws (map #(keyword (str %))coll)
        letters (for [x kws :let [y (get key-mapper x)]] y)
        ]
    (string/join letters)
    )
  )
(testfunccc [104 101 104 13])




(for [x [:104 :101 :104 :13]
      :let [y (get key-mapper x)]]
  y)

(map #(+ % 5) '(1 2 3))
(map #(keyword (str %))[66 67])

(keyword (str 88))

(get key-mapper (keyword (str 75)))

(get key-mapper [:65 :66])
(select-keys key-mapper [:65 :66])
(vals (select-keys key-mapper [:65 :66]))
(keyword (str 88))
(get key-mapper :88)
(keyword 88)
(Integer/parseInt (name :88))
(str 88)
(keyword (str 88))