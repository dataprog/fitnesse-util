(ns org.dataprog.fitnesse.fixture.cmd.teardown
  (:require [clojure.java.shell :as sh]
            [clojure.string :as str])
  (:gen-class
   :name    org.dataprog.fitnesse.fixture.cmd.CmdTearDown
   :prefix  "-"
   :methods [[tearDown [] void]]))

(defn -tearDown [_] (shutdown-agents))
