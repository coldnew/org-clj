(ns coldnew.org-clj.parser
  #?(:cljs
     (:require-macros [coldnew.org-clj.parser :refer [generate-bnf]]))
  (:require [instaparse.core :as insta]))

;; https://github.com/danneu/klobbdown/blob/master/src/klobbdown/parse.clj
;; https://github.com/eigenhombre/yaclomp/blob/master/resources/grammar.bnf
;; https://github.com/gmorpheme/organum/blob/master/src/organum/core.clj
;; https://github.com/minikomi/orjmode/blob/master/resources/org.bnf
;; http://orgmode.org/manual/Document-preamble.html
(comment
  (def bnf
    (slurp (clojure.java.io/resource "preamble.bnf"))
    )
  )
(comment
  (defn generate-bnf
    []
    (slurp (clojure.java.io/resource "preamble.bnf")))
  )

#?(:clj
   (defmacro generate-bnf []
     (clojure.core/slurp (clojure.java.io/resource "preamble.bnf"))))

(defn org-to-ast
  "Convert org-mode to AST"
  [content]
  ((insta/parser
    ;; TODO: fix for cljs
    (generate-bnf))
   content))

(comment
  (org-to-ast "* hello \n")
  (org-to-ast "** hello")
  (org-to-ast "*** hello\n")
  (org-to-ast "*asd*")

  (org-to-ast "#+TITLE: hello wol\n#+AUTHOR: coldnew")
  (parse-org "#+TITLE: hello wol\n#+AUTHOR: coldnew\n* asd\n** asds")
  )

(defn ast-to-hashmap
  "Convert AST to hashmap"
  [ast]
  (->> ast vec (into {})))

(defn parse-org [content]
  (->> content
       org-to-ast
       ast-to-hashmap))

(defn- parse-with-key [content key]
  (->> content parse-org key))

(defn parse-title [content]
  (parse-with-key content :title))

(defn parse-author [content]
  (parse-with-key content :author))

(defn parse-creator [content]
  (parse-with-key content :creator))

(defn parse-date [content]
  (parse-with-key content :date))

(defn parse-email [content]
  (parse-with-key content :email))

(defn parse-language [content]
  (parse-with-key content :language))

(defn parse-select-tags [content]
  (parse-with-key content :select-tags))

(defn parse-exclude-tags [content]
  (parse-with-key content :exclude-tags))

(defn parse-heading [content]
  (parse-with-key content :heading))

(comment
  (parse-title "#+TITLE: This is title\n#+AUTHOR:coldnew")
  (parse-title "#+TITLE:This is title")
  (parse-title "#+TITLE:  This is title")
  (parse-author "#+AUTHOR: Yen-Chin, Lee")
  )
