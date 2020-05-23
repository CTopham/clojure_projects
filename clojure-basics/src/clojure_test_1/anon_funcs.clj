(ns clojure-test-1.anon-funcs
  (:require [clojure.string :as string :refer [split join]])
  )

;-------- Only get the capital letters

(defn getCaps [s]
  (string/join "" (filter #(Character/isUpperCase %1) (seq s))))

(getCaps "$#A(*&987Zf")


;------------- regex
(apply str (re-seq #"[A-Z]+" "bA1B3Ce "))

;------------- stats func
(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))

(defn stats [numbers]
  (map #(% numbers) [sum count avg]))

(stats [1 2 3])
