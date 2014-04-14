(ns org.dataprog.fitnesse.fixture.clj
  (:require [clojure.string :as str])
  (:gen-class
   :name    org.dataprog.fitnesse.fixture.clj.CljFixture
   :state   state
   :init    init
   :prefix  "-"
   :methods [[setIn  [String] void  ]
             [result [      ] Object]
             [out    [      ] String]
             [err    [      ] String]]))

(defn evaluate
  [in]
  (let [sw-out (new java.io.StringWriter)
        sw-err (new java.io.StringWriter)]
    (binding [*out* sw-out
              *err* sw-err]
      {:result (eval (read-string in))
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
