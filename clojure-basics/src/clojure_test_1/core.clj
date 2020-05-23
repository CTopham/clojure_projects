(ns clojure-test-1.core
  (:require [clojure.string :as string :refer [split join]])
  )

; hashmap that translates greeting
(def translations
  {:english
   {:unknown/not-understood "%s don't understand the command %s"
    :unknown/parsed-this-msg-as "parsed this msg as follows: sender: %s %s cmd: %s data: %s"
    :hello/hello "Hello %s %s!"}

   :french
   {:hello/hello "Bonjour %s %s!"}

   :spanish
   {:hello/hello "Hola %s %s!"}

   :chinese
   {:hello/hello "Ni hao %s %s!"}})

;;print keyS and a value in the translation hm
(println "translation keys")
(keys translations)

; this will print craig bot before our response
(def bot-name "Craig Bot")

;this splits our string
(defn words [s]
  (split s #" "))

;this gets our first name of the input
(defn first-name [s]
  (first (words s)))

;this gets our second name of the input
(defn last-name [s]
  (last (words s)))

;this gets the first word of the greeting
(defn msg-greeting [msg]
  (first (words msg)))

;reply formats the string,
(defn handle-hello [reply sender msg]
  (reply [[(msg-greeting msg) " " (first-name sender) " " (last-name sender)]]))

;;handles the errors, this will happen when the greeting doesnt start with "hello"
(defn handle-unknown [reply sender msg]
  (let [fname (first-name sender)
        lname (last-name sender)
        cmd   (first (words msg))
        data  (rest (words msg))]
    (reply [[ bot-name " doesn't understand the command," cmd]
            [bot-name "parsed as" fname lname "cmd" cmd] "data" data])))

; Combinator
(defn handle-msg [reply sender msg]
  (let [cmd (first (words msg))]
    (if (= cmd "Hello")
      (handle-hello reply sender msg)
      (handle-unknown reply sender msg))))


(defn -main
  [& args]
  (let [sender   (first args)
        msg      (second args)
        reply    (fn [parts] (println parts))]              ;same as #(println %)
    (handle-msg reply sender msg)))


(-main "Craig Topham" "Hello there!")
(-main "Craig Topham" "Do something!")




;--- equations-----------
((fn add-five [x] (+ x 5)) 3)
(#(+ % 5)3)
((fn [x] (+ x 5)) 3)
((partial + 5) 3)

;------------------------

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(stats [1 2 3])                                             ;returns(sum, count, average)

;------------First Number in Fibonacci sequence

(defn firstFib [n]
  (let [fib (fn fib* [a b]
              (cons a (lazy-seq (fib* b (+ b a)))))]
    (take n (fib 1 1))))

(firstFib 5)




