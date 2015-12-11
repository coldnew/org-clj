(ns coldnew.org-clj.parser.preamble
  (#?(:clj :require :cljs :require-macros)
   [coldnew.org-clj.private.utils :refer [slurp-resource]])
  (:require [instaparse.core :as insta]
            ;;[environ.core :refer [env]]
            ))

;; for repl dev
(comment
  (ns coldnew.org-clj.parser.preamble
    (:require [instaparse.core :as insta]
              [coldnew.org-clj.private.utils :refer [slurp-resource]]))
  )

(defn generate-bnf []
  (slurp-resource "preamble.bnf"))

(defn to-ast [content]
  ((insta/parser
    ;;(generate-bnf)
    (clojure.core/slurp (clojure.java.io/resource "preamble.bnf"))
    )
   content))

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

;; TODO: remove
(comment

  (defn verify-header
    [{:keys [meta content callback-fn]}]
    (= (callback-fn (str meta content))
       (callback-fn (str meta content "\n"))
       (callback-fn (str meta content "\n\n"))
       (callback-fn (str meta " " content))
       (callback-fn (str meta "   " content))
       (callback-fn (str meta "\t" content))
       content))

  (to-ast (slurp-resource "test1.org"))

  #_(parse "#+TITLE: This is title\n#+AUTHOR:coldnew #+DATE: asd")

  (into {}
        (filter #(not= (first %) :content) {:a 1 :b 2 :content 4}))

  (into {}
        (filter #(not= (first %) :content)
                (parse (slurp-resource "test1.org"))))
  (=
   (->> (parse (slurp-resource "test1.org"))
        (filter #(not= (first %) :content))
        (into {})
        )
   {:title "org-clj 簡易測試"
    :author "Yen-Chin, Lee"
    :email "coldnew.tw@gmail.com"
    :language "zh-tw"
    })

  (=
   (parse (slurp-resource "test1.org"))
   {:title "org-clj 簡易測試"
    :author "Yen-Chin, Lee"
    :email "coldnew.tw@gmail.com"
    :language "zh-tw"
    :content "content"
    })

  )