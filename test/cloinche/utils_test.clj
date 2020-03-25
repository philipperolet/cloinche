(ns cloinche.utils-test
  (:require [clojure.test :refer :all]
            [clojure.spec.test.alpha :as st]
            [cloinche.utils :refer :all]))

(deftest gen-permutation-tests
  (testing "Correct specs"
    (is (= 1 (-> (st/check `gen-permutation)
                 (st/summarize-results)
                 (:check-passed)))))
  
  (testing "Multiple calls should generate different permutations"
    (let [perm1 (gen-permutation 10)
          perm2 (gen-permutation 10)
          perm3 (gen-permutation 10)]
      (is (not (= perm1 perm2)))
      (is (not (= perm2 perm3)))
      (is (not (= perm1 perm3))))))
