(ns clojure-test-1.closures-comp-partials
  )
;---interop
(import java.util.Date)

(def now (Date.))
(str now)
;====================== Closures ======================

; Would closures every be used with out returning another function?

(defn adder [n]                                               ;internal function has access to n
  (fn [a] (+ n a)))
(def add-five (adder 5))
(add-five 100)

;--------------------
(defn add-nine [n]
  (let [a (fn [a] (+ n a))
        b (a 9)]
    b))

(add-nine 10)
;--------------------

(defn add-ten [n]
  (let [a (fn [a] (+ n a))]
    (a 10)))

(add-ten 5)

;------ A closure with a partial in it-----------------------

;takes a function and any varying amount of args
(defn my-partial [f partial-args]
  (fn [& args]
  (apply f (concat partial-args args))))                    ;concatting the args

(def craig-says (my-partial str ["craig " "says " 2]))        ;str is a function
(craig-says " is all you need")


;======================partials======================
;The partial function takes a function and fewer than the normal arguments to the function,
; and returns a function that takes a variable number of additional args.
;you can use this to attach a static arg to function.
(def hundred-times (partial * 100))
(hundred-times 2 2 2)

; a partial with multiple parameters
(defn add-partial [x y] (+ x y))
(add-partial 1 2)


;another partial----
;helper
(defn letter? [ch]
  (Character/isLetter ch))

(defn just-letter [str]
  (filter (partial letter?) str)
  )
(just-letter "h2i")

;this is a ters way of the above function
(defn ters [str]
  (let [letter (filter #(Character/isLetter %)str)]
    letter))

(ters "a d c 2")

;======================comp===========================

;;Comp takes a series of functions and composes them into a new function
; comp executes inner most instruction take the result of that and passes it into the next inner most function
(def p4-inc
  (comp inc #(* 4 %)))
(p4-inc 2)
