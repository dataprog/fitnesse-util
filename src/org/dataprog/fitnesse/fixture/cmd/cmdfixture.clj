(ns org.dataprog.fitnesse.fixture.cmd.cmdfixture
  (:require [clojure.java.shell :as sh]
            [clojure.string     :as str])
  (:gen-class
   :name    org.dataprog.fitnesse.fixture.cmd.CmdFixture
   :state   state
   :init    init
   :prefix  "-"
   :methods [ [setCmd [String] void   ]
              [exit   [      ] int    ]
              [out    [      ] String ]
              [err    [      ] String ]]))

(defn -init   [        ] [[] (atom {})])
(defn -setCmd [this cmd] (reset!  (.state this) (apply sh/sh (str/split cmd #"\s"))))
(defn -exit   [this    ] (:exit  @(.state this)))
(defn -out    [this    ] (:out   @(.state this)))
(defn -err    [this    ] (:err   @(.state this)))
