(ns org.dataprog.fitnesse.fixture.cmd.setup
  (:require [clojure.java.shell :as sh]
            [clojure.string :as str])
  (:gen-class
   :methods [[finalize [] void]]))

(defn -finalize [_] (shutdown-agents))
