(defproject org.dataprog/cmdfixture "0.1.4-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :aot           [org.dataprog.fitnesse.fixture.cmd.cmdfixture
                  org.dataprog.fitnesse.fixture.cmd.bashfixture
                  org.dataprog.fitnesse.fixture.cmd.setup])
