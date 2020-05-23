(ns clojure-test-1.string-stuff)

;-------------- simple string split and println----

(defn nameSplit [x]
  (let [split (split x #" ") firstname(first split) lastname(second split)]
    (println "first name" firstname "and" "last name" lastname)))

(nameSplit "craig topham")

(defn concatstr [name]
  (str "Hello, " name "!"))
(concatstr "craig")
;--------------
(let [[first second] "abcde"]
  (prn 'firstone= first)
  (prn 'secondone second))
