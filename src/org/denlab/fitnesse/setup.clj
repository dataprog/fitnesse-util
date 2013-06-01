(ns org.denlab.fitnesse.setup
  (:require [clojure.java.shell :as sh]
            [clojure.string :as str])
  (:gen-class
   :methods [[finalize [] void]]))

(defn -finalize [_] (shutdown-agents))
