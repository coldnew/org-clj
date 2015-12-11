(ns coldnew.org-clj.parser.content
  (#?(:clj :require :cljs :require-macros)
   [coldnew.org-clj.private.utils :refer [slurp-resource]])
  (:require [instaparse.core :as insta]
            ;;[environ.core :refer [env]]
            ))

;; for repl dev
(comment
  (ns coldnew.org-clj.parser.content
    (:require
     [coldnew.org-clj.private.utils :refer [slurp-resource]])
    (:require [instaparse.core :as insta]
              ;;[environ.core :refer [env]]
              ))
  )

(defn generate-bnf []
  (slurp-resource "content.bnf"))

(defn to-ast [content]
  ((insta/parser
    ;;(generate-bnf)
    (clojure.core/slurp (clojure.java.io/resource "content.bnf"))
    )
   content))

(defn ast-to-hashmap
  "Convert AST to hashmap"
  [ast]
  (->> ast vec (into {})))

#_(->
   (to-ast "* TODO asdasd\n ** bbb")
   ast-to-hashmap)