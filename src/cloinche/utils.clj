(ns cloinche.utils
  "Utilities to:
  - generate random permutations
  NOTE: permutation generation is actually useless since there is shuffle."
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]))

;; a vector is a permutation if when sorted it is the n first integers (0 included)
(s/def ::permutation (s/and (s/coll-of int? :kind vector?)
                            #(= (sort %) (range (count %)))))

(s/fdef gen-permutation
  :args (s/cat :size (s/int-in 0 1000)) ;; limited to 1000 here to avoid long testing times
  :ret ::permutation
  :fn #(= (-> % :args :size) (count (:ret %))))

(defn gen-permutation [size]
  (shuffle (vec (range size))))

(defn generate-permutation-slow [size]
  (let [initial-perm (vec (range size))]
    (reduce
     (fn [current-perm val]
       (let [swap-index (rand-int (- size val))]
         (assoc current-perm swap-index (last current-perm) (dec size) (current-perm swap-index))))
     initial-perm
     initial-perm)))
