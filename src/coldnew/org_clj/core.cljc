(ns ^:no-doc coldnew.org-clj.core
  ;;(:require [coldnew.org-clj.parser :as p])
  )

;; This file is for test how to show the file
(comment
  ;; enable *print-fn* in clojurescript
  (enable-console-print!)

  (defn -main [& args]
    (println (p/generate-bnf))
    )

  ;; setup node.js starter point
  (set! *main-cli-fn* -main)
  )