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

(defn parse-org [content]
  ((insta/parser
    (generate-bnf)) content))
;; (parse-org "#+TITLE:  asd")
;; (parse-org "#+AUTHOR:  asd")

(defn content->hashmap [content]
  (->> content
       parse-org
       vec
       (into {})))

(defn parse-title [content]
  (->> content
       content->hashmap
       :title))

(defn parse-author [content]
  (->> content
       content->hashmap
       :author))

(defn parse-email [content]
  (->> content
       content->hashmap
       :email))

(comment
  (parse-title "#+TITLE: This is title")
  (parse-title "#+TITLE:This is title")
  (parse-title "#+TITLE:  This is title")
  (parse-author "#+AUTHOR: Yen-Chin, Lee")
  )
