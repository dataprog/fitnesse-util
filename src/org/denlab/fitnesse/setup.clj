(ns org.denlab.fitnesse.setup
  (:require [clojure.java.shell :as sh]
            [clojure.string :as str])
  (:gen-class
   :methods [[finalize [boolean] void]]))

(defn -finalize [_ _] (shutdown-agents))
