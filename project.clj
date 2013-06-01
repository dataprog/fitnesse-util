(defproject org.denlab.fitnesse/cmdfixture "0.1.4-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :aot           [org.denlab.fitnesse.cmdfixture
                  org.denlab.fitnesse.bashfixture
                  org.denlab.fitnesse.setup])
