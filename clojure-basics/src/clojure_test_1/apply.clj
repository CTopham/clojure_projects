(ns clojure-test-1.apply
  (:require [clojure.string :as string :refer [split join]])
  )
;-----------------------Apply-----------------------
;Dynamically specify a function being called
;example
(max 1 4 2)
(max [1 2 3])
(apply max [1 2 3])

;another example
(defn foo [a b]
  (prn a b))
(apply foo [:A :B])

;another example

(defn print-before [msg f args]                             ;takes a message, a function, and then the args for that function and applies it to all args
  (prn msg)
  (apply f args))

(print-before "incrementing" inc [1])
(print-before "concat string" str ["space" "space"])