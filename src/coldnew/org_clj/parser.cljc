(ns coldnew.org-clj.parser
  (#?(:clj :require :cljs :require-macros)
   [coldnew.org-clj.private.utils :refer [slurp-resource]])
  (:require [instaparse.core :as insta]
            [coldnew.org-clj.parser.preamble :as preamble]
            ))

;; for repl dev
(comment
  (ns coldnew.org-clj.parser
    (:require [instaparse.core :as insta]
              [coldnew.org-clj.parser.preamble :as preamble]
              [coldnew.org-clj.private.utils :refer [slurp-resource]]
              ))
  )
;; https://github.com/danneu/klobbdown/blob/master/src/klobbdown/parse.clj
;; https://github.com/eigenhombre/yaclomp/blob/master/resources/grammar.bnf
;; https://github.com/gmorpheme/organum/blob/master/src/organum/core.clj
;; https://github.com/minikomi/orjmode/blob/master/resources/org.bnf
;; http://orgmode.org/manual/Document-preamble.html

(defn to-ast [content]
  (let [preamble (preamble/parse content)]
    preamble)
  )

(comment
  (to-ast "#+TITLE: This is title\n#+AUTHOR:coldnew")
  (parse-title "#+TITLE:  This is title")
  (parse-author "#+AUTHOR: Yen-Chin, Lee")
  (parse-author "")
  )
