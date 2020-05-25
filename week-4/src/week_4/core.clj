(ns week-4.core
  (:require [clojure.string :as string :refer [split join]])
  )



; James Vu - Module 1 Challenge 1: Implement a function to count how many times a given character appears in a given word.
(defn count-character [word, letter]
  ; Split the word into characters.
  ; Initialize an atom-type counter variable at 0 to keep track of duplicate characters.
  ; Iterate through each character.
  ; If a character iterated over matches the given letter, increase the duplicate-counter variable by a value of 1.
  ; Return the value of the duplicate-character counter variable at the end.
  (let [
        char-arr (string/split word #"")                    ;make an array of chars
        str-let (str letter)                                ;cast letter to str
        result (count (filter #(= % str-let) char-arr))     ;filter then count
        ]
    result)
  )


;; Sam Hays - Week 0 Challenge 3
(defn strman [msg]
  ;; @TODO
  ;; Take a string, e.g. "this is a string" -
  ;; Capitalize all characters and add a dash between:
  ;; "THIS - IS - A - STRING"
(string/replace (string/upper-case msg) " " " - ")
  )


;;Ryan Boyce: Week 1 challenge 1
(defn compareStr
  [str1 str2]
  ;;@TODO
  ;;The classic "compare two strings function"
  ;;return 0 if equal, positive number if str1 is greater in length, and negative number if str2
  ;;is greater in length
  (cond
    (= (count str1) (count str2)) 0
    (> (count str1) (count str2)) 1
    (< (count str1) (count str2)) -1
    ))

;;Ryan Boyce: Week 1 challenge 2
(defn prime-num
  [num]
  ;;@TODO
  ;;Return all primes in a list of number
  (filter #(not= 0 (rem %1 2)) num))

(prime-num [1 2 3])


;;Drake Ellis: Week 0 Challenge 3
(defn random-number [n]
  ;; @TODO
  ;; Without using any built in Clojure commands, write a function that will return a random
  ;; integer from 0 to n.
  (rand-int n)
  )

;;Drake Ellis: Week 0 Challenge 1
(defn temperature-converter [fahrenheit]
  ;;@TODO
  ;; Write a function that converts a temperature in Fahrenheit to Celsius.
  (let [a (- fahrenheit 32)
        b (* a 5)
        result (/ b 9)]
    result)
  )
(temperature-converter 35)
;------------------------------module 2------------------------------------------------

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


;---------my questions week 4-----------------------comp, protocol, integrant, reduce, partial

(def friends {:bob {:age 40 :type "best"} :jessica {:age 29 :type "funny"} :adam {:age 36 :type "peer"}})

(defn best-friend-finder [m]
  (let [bob (get-in friends [:bob :type])
        jessica (get-in friends [:jessica :type])
        adam (get-in friends [:adam :type])
        friends {:bob bob :jessica jessica :adam adam}
        bestfriend (filter (comp #{"best"} last) friends)
        ]
    bestfriend))

(best-friend-finder friends)

;Big things have happened.. Adam brought pizza into the
;office for his birthday, he is now your new best friend.
;increment adams age and change his friend type to "best"
(defn new-bestfriend [m]
  (-> m
      (assoc :bob {:age 40 :type "peer"})
      (assoc :adam {:age 37 :type "best"})
     ))

(new-bestfriend friends)

;----protocol------
;in the following 3 functions create a protocol in which you define a Picnic basket
;implement a defrecord which stores items in the basket
;then define a function to add some wine as a beverage type to the picnic basket
(defprotocol Picnic
  (picnic [this] "Method to pack"))

(defrecord Item [name type]
  Picnic
  (picnic [this] (str (:name this) " has been added to the basket")))

(def wine (Item. "wine" "beverage"))

(picnic wine)

;------------------------------------Questions---------------------


;1. By taking a more functional approach to dependency injection using Integrant, would this be considered a heavier lean on IoC,
;   is there any negative impacts of taking such an approach?

;2. When implementing functions that use polymorphic dispatch in Clojure, how does this compare to just an ordinary
;   if/else statement or the template method pattern?

;3. When learning about protocols, would the purpose of these serve to accomplish what an ADT can in Java?