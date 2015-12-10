(defproject coldnew/org-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.189"]
                 [com.lucasbradstreet/instaparse-cljs "1.4.1.0"]]

  :test-paths ["spec"]
  :source-paths ["src"]

  :plugins [[speclj "3.3.0"]
            [lein-codox "0.9.0"]]
  :profiles {:dev {:dependencies [[speclj "3.3.0"]]}}

  :codox {:source-uri "http://github.com/coldnew/org-clj/blob/master/{filepath}#L{line}"})
