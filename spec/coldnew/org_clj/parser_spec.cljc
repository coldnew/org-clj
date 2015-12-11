(ns coldnew.org-clj.parser-spec
  (#?(:clj  :require
      :cljs :require-macros)
   [speclj.core :refer [describe it should should-not]]
   [coldnew.org-clj.private.utils :refer [slurp-resource]])
  (:require [speclj.run.standard :refer [run-specs]]
            [coldnew.org-clj.parser
             :refer [ to-ast ]]))

(defn verify-tree [file ast]
  (it (str "Verify org-mode file with AST tree\n" file)
      (should (= (to-ast file) ast))))

(describe
 "Document to AST"

 (verify-tree (slurp-resource "test1.org")
              {:title "org-clj 簡易測試"
               :author "Yen-Chin, Lee"
               :email "coldnew.tw@gmail.com"
               :language "zh-tw"
               })
 )
