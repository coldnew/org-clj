(ns coldnew.org-clj.parser
  (:require [instaparse.core :as insta]))

;; https://github.com/danneu/klobbdown/blob/master/src/klobbdown/parse.clj
;; https://github.com/eigenhombre/yaclomp/blob/master/resources/grammar.bnf
;; http://orgmode.org/manual/Document-preamble.html
(def bnf
  (slurp (clojure.java.io/resource "grammar.bnf"))
  )

(defn generate-bnf
  []
  (slurp (clojure.java.io/resource "grammar.bnf")))

(def parse-org (insta/parser
                (generate-bnf))
  )
;;(parse-org "#+TITLE: a")