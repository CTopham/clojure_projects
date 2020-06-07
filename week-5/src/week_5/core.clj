(ns week-5.core
  (:require [clojure.spec.alpha :as spec])
  (:require [clojure.string :as string :refer [split join]])
  (:require [clojure.pprint :as p])
  )


;--------------- module 2-----------------------------

(def grades [
             {:name "Jeffery" :exam1 55 :exam2 60 :exam3 80}
             {:name "Troy" :exam1 65 :exam2 70 :exam3 80}
             {:name "Abed" :exam1 75 :exam2 80 :exam3 85}
             {:name "Shirley" :exam1 85 :exam2 80 :exam3 90}
             {:name "Annie" :exam1 100 :exam2 90 :exam3 85}
             {:name "Britta" :exam1 60 :exam2 70 :exam3 80}])

(defn exam-average [exam gradeVector]
  ;;@TODO
  ;; Write a function that takes in an exam name and the grade vector
  ;; and returns the class average on that exam
  (let [exam1 (/ (reduce + (map #(:exam1 %) gradeVector)) (count (map #(:exam1 %) gradeVector)))
        exam2 (/ (reduce + (map #(:exam2 %) gradeVector)) (count (map #(:exam2 %) gradeVector)))
        exam3 (/ (reduce + (map #(:exam3 %) gradeVector)) (count (map #(:exam3 %) gradeVector)))]
    (cond
      (= exam 1) exam1
      (= exam 2) exam2
      (= exam 3) exam3
      )))


;(map #(:exam1 %) grades)
;(reduce + (map #(:exam3 %) grades))
;(count (map #(:exam3 %) grades))

(exam-average 3 grades)

(defn class-average [gradesVector]
  ;;@TODO
  ;; Write a function that takes the grade vector
  ;; and returns the class average
  (let [exam1  (reduce + (map #(:exam1 %) gradesVector))
        exam2 (reduce + (map #(:exam2 %) gradesVector))
        exam3 (reduce + (map #(:exam3 %) gradesVector))
        sum (+' exam1 exam2 exam3)]
    (/ sum 18))
  )

(class-average grades)

(defn student-average [v student]
  ;;@TODO
  ;; Write a function that takes in a name and the grade vector
  ;; and returns that student's grade average
  (let [jeffery (float (/ (int (reduce + (nthrest (vals (get v 0))1)))3))
         troy (float (/ (int (reduce + (nthrest (vals (get v 1))1)))3))
         abed (float (/ (int (reduce + (nthrest (vals (get v 2))1)))3))
         shirley (float (/ (int (reduce + (nthrest (vals (get v 3))1)))3))
         annie (float (/ (int (reduce + (nthrest (vals (get v 4))1)))3))
         britta (float (/ (int (reduce + (nthrest (vals (get v 5))1)))3))
         student-lowercase (clojure.string/lower-case student)]
        (cond
          (= student-lowercase "jeffrey") (print "Jeffreys grade average is" jeffery)
          (= student-lowercase "troy") (print "troy grade average is" troy)
          (= student-lowercase "abed") (print "abed grade average is" abed)
          (= student-lowercase "shirley") (print "shirley grade average is" shirley)
          (= student-lowercase "annie") (print "annie grade average is" annie)
          (= student-lowercase "britta") (print "britta grade average is" britta)
          )))


;;Drake Ellis: Week 1 Challenge 3
(defn speed-test [m]
  ;; @TODO
  ;; Write a function that tests the speed of retrieving multiple items from a map.
  ;; The map could be of any size, but must have a significant number of keys requested
  ;; during the test.
  (let [time (time (vals m))]
    time
  ))
(speed-test {:a "a" :b "b" :c "c"})

; James Vu - Module 2 Challenge 1: Using the concept of higher-order functions, filter out the common numbers between 2 sets before sorting the results in order from least to greatest and finally fetching the 1st number from here.
(defn filter-sort-extract-numbers [set1, set2]
  ; Filter the common numbers in both sets.
  ; Sort the results.
  ; Extract the first value from the sorted filter.
  (let [common (clojure.set/intersection set1 set2)
        sorted-common (sort common)
        get-first (first sorted-common)
        ]
    get-first
        )
  )
(filter-sort-extract-numbers #{1 2 3 4} #{3 4 5 6 7})


; James Vu - Module 2 Challenge 2: Given the following list, extract the 1st, middle, and last names and print them together in a sentence via destructuring.
(defn create-sentence [names]
  ; Destructure the names and print a sentence using only the selected names.
  (let [ f (:first names)
        m (:middle names)
        l (:last names)
        sentence (string/join " " [f m l])]
    (p/pprint sentence)))

(create-sentence {:first "craig" :middle "m" :last "topham"})

;-----------new questions week5 -----------------------------------

;Write a spy function
(defn spy [tag v]
  (println tag v)v)

;Use the spy function above to see whats going on in this threading macro
(defn i-spy []
  (->> (range 5)
       (spy "I spy; the range")
       (filter even?)
       (spy "I spy; just evens")
       (map #(* % %) ,,,)
       (spy "I spy; sq each number")
       (reduce + ,,,)))
;output should be
  ;I spy; the range (0 1 2 3 4)
  ;I spy; just evens (0 2 4)
  ;I spy; sq each number (0 4 16)

;write a spec to figure out if darth-vader still conforms to the sith.
(spec/def ::jedi-or-sith
  (spec/and has-force? is-sith?))

(spec/conform ::jedi-or-sith "darth vader")

;used for ::jedi-or-sith spec
(defn has-force? [x]
  (= x "yoda")
  (= x "darth vader")
  )
;used for ::jedi-or-sith spec
(defn is-sith? [x]
  (= x "darth vader"))

;-----------------------questions-------------------------------

;1. When taking a property based testing approach to developing systems in Clojure, would this close the door
;   on using TDD to explore system design.

;2 Under what circumstances or systemic design would the additional cpu cost of
; keeping instrumentation on in production be the right choice?

;3 Is there any tools that exist to decipher stacktraces?