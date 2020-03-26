(ns cloinche.state-test
  (:require [clojure.test :refer :all]
            [clojure.spec.test.alpha :as st]
            [cloinche.state :as state :refer :all]))

(deftest create-new-state--check
  (is (= nil (-> (st/check `create-new-state)
                 first
                 st/abbrev-result
                 (#(if (:failure %) % nil))))))

(deftest create-new-state--??
  (testing "Returns a new state, ordered"
    (is (=
         (create-new-state)
         {::state/step :initial
          ::state/trump :undecided
          ::state/deck (range 32)})))
  
  (testing "Returns a new state with the given deck"
    (is (=
         (create-new-state '(2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 1 0))
         {::state/step :initial
          ::state/trump :undecided
          ::state/deck '(2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 1 0)})))
  
  (testing "Fails if deck is not the right size"
    (is (thrown-with-msg?
         AssertionError
         #"valid-deck"
         (create-new-state '(2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31)))))
  
  (testing "Fails if deck is not a permutation of 0..31"
    (is (thrown-with-msg?
         AssertionError
         #"valid-deck"
         (create-new-state '(2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 1 1))))))
