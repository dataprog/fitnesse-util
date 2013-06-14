(ns org.denlab.fitnesse.cmdfixture
  (:require [clojure.java.shell :as sh]
            [clojure.string :as str])
  (:gen-class
   :methods [ [setCmd [String] void   ]
              [exit   [      ] int    ]
              [out    [      ] String ]
              [err    [      ] String ]]))

(def results (atom {}))

(defn -setCmd [this cmd] (swap! results assoc this (apply sh/sh (str/split cmd #"\s"))))
(defn get-and-shutdown [this k] (k (@results this)))
(defn -exit   [this] (get-and-shutdown this :exit))
(defn -out    [this] (get-and-shutdown this :out ))
(defn -err    [this] (get-and-shutdown this :err ))
