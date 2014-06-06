(ns org.dataprog.fitnesse.fixture.cmd.bashfixture
  (:require [clojure.java.shell :as sh])
  (:gen-class
   :name    org.dataprog.fitnesse.fixture.cmd.BashFixture
   :state   state
   :init    init
   :prefix  "-"
   :methods [[setCmd   [String] void   ]
             [exit     [      ] int    ]
             [out      [      ] String ]
             [err      [      ] String ]]))

(defn -init   [        ] [[] (atom {})])
(defn -setCmd [this cmd] (reset! (.state this) (sh/sh "bash" "-c" cmd)))
(defn -exit   [this    ] (:exit @(.state this)))
(defn -out    [this    ] (:out  @(.state this)))
(defn -err    [this    ] (:err  @(.state this)))
