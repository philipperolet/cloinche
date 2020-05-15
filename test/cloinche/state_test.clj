(ns cloinche.state-test
  (:require [clojure.test :refer :all]
            [clojure.spec.test.alpha :as st]
            [cloinche.state :as state :refer :all]))

(deftest create-new-state--test-specs
  (is (= nil (-> (st/check `create-new-state)
                 first
                 st/abbrev-result
                 (#(if (:failure %) % nil))))))

(deftest cut--test
  (testing "Correct specs"
    (is (= nil (-> (st/check `cut)
                   first
                   st/abbrev-result
                   (#(if (:failure %) % nil)))))))
;  (testing "Fails if state is not initial"
;    (is (thrown? ArithmeticException (cut {::step :initial
;                                           ::trump :undecided
;                                           ::deck (range 32)})))))
