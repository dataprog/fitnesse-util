(ns org.dataprog.fitnesse.fixture.clj
  (:require [clojure.string :as str]
            [clojure.main   :as main])
  (:import [clojure.lang LineNumberingPushbackReader])
  (:gen-class
   :name    org.dataprog.fitnesse.fixture.clj.CljFixture
   :state   state
   :init    init
   :prefix  "-"
   :methods [[setIn  [String] void  ]
             [result [      ] Object]
             [out    [      ] String]
             [err    [      ] String]]))

(defn- initialize
  "Common initialize routine for repl, script, and null opts"
  [args inits]
  (in-ns 'user)
  (set! *command-line-args* args)
  (doseq [[opt arg] inits]
    ((init-dispatch opt) arg)))

;; copied from clojure.main/eval-opt
;; TODO could be rewritten shortly
(defn eval-opt
  "Evals expressions in str, return last result"
  [str]
  (let [eof (Object.)
        reader (LineNumberingPushbackReader. (java.io.StringReader. str))]
    (loop [input       (main/with-read-known (read reader false eof))
           last-result nil]
      (if (= input eof)
        last-result
        (let [value (eval input)]
          value
          (recur (main/with-read-known (read reader false eof))
                 value))))))

(defn evaluate
  [in]
  (let [sw-out (new java.io.StringWriter)
        sw-err (new java.io.StringWriter)]
    (binding [*out* sw-out
              *err* sw-err]
      {:result #_(eval-opt in) (main/main "-e" in)
       :out    (str sw-out)
       :err    (str sw-err)})))

(defn sanitize-input
  [in] (-> in
           (str/replace #"^<pre>"  "")
           (str/replace #"</pre>$" "")))

(def fit-evaluate (comp evaluate
                        sanitize-input))

(defn -init   [        ] [[] (atom {})])
(defn -setIn  [this in ] (reset!   (.state this) (fit-evaluate in)))
(defn -result [this    ] (:result @(.state this)))
(defn -out    [this    ] (:out    @(.state this)))
(defn -err    [this    ] (:err    @(.state this)))
