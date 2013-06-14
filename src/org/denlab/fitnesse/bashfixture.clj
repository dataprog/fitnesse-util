(ns org.denlab.fitnesse.bashfixture
  (:require [clojure.java.shell :as sh]
            [clojure.string :as str])
  (:gen-class
   :methods [[setCmd   [String] void   ]
             [exit     [      ] int    ]
             [out      [      ] String ]
             [err      [      ] String ]]))

(def results (atom {}))

(defn -setCmd [this cmd] (swap! results assoc this (sh/sh "bash" "-c" cmd)))
(defn get-result [this k] (k (@results this)))
(defn -exit   [this] (get-result this :exit))
(defn -out    [this] (get-result this :out ))
(defn -err    [this] (get-result this :err ))
