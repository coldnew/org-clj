(ns coldnew.org-clj.parser.preamble
  (#?(:clj :require :cljs :require-macros)
   [coldnew.org-clj.private.utils :refer [slurp-resource]])
  (:require [instaparse.core :as insta]
            ;;[environ.core :refer [env]]
            ))

;; for repl dev
(comment
  (ns coldnew.org-clj.parser.preamble
    (:require [instaparse.core :as insta]))
  )

(defn generate-bnf []
  (slurp-resource "preamble.bnf"))

(defn to-ast [content]
  ((insta/parser (generate-bnf)) content))

(defn ast-to-hashmap
  "Convert AST to hashmap"
  [ast]
  (->> ast vec (into {})))

(defn parse [content]
  (->> content
       to-ast
       ast-to-hashmap))

(defn- parse-with-key [content key]
  (->> content parse key))

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

;;(parse "#+TITLE: This is title\n#+AUTHOR:coldnew\nasdasd")