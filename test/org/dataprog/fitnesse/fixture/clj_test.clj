(ns org.dataprog.fitnesse.fixture.clj-test
  (:require [clojure.string :as str]
            [org.dataprog.fitnesse.fixture.clj :refer :all]
            [clojure.test                      :refer :all]))

(deftest sanitize-input-test
  (are [in ex] (= (sanitize-input in) ex)
       "<pre>(+ 1 1)</pre>"         "(+ 1 1)"
       "(+ 1 1)"                    "(+ 1 1)"
       "(str \"<pre>\" \"</pre>\")" "(str \"<pre>\" \"</pre>\")"))

(deftest fit-evaluate-test
  (are [in ex] (= (fit-evaluate in) ex)
       "<pre>(+ 1 1)</pre>"         {:result 2             :out "" :err ""}
       "(+ 1 1)"                    {:result 2             :out "" :err ""}
       "(str \"<pre>\" \"</pre>\")" {:result "<pre></pre>" :out "" :err ""}))
