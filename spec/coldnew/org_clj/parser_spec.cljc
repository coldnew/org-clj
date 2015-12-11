(ns coldnew.org-clj.parser-spec
  (#?(:clj  :require
      :cljs :require-macros)
   [speclj.core :refer [describe it should should-not]]
   [coldnew.org-clj.private.utils :refer [slurp-resource]])
  (:require [speclj.run.standard :refer [run-specs]]
            [coldnew.org-clj.parser
             :refer [parse-org
                     parse-title parse-author parse-creator parse-date parse-email parse-language
                     parse-select-tags parse-exclude-tags] ]))

(defn verify-header
  [{:keys [meta content callback-fn]}]
  (it (str meta " parser")
      (should (= (callback-fn (str meta content))
                 (callback-fn (str meta content "\n"))
                 (callback-fn (str meta content "\n\n"))
                 (callback-fn (str meta " " content))
                 (callback-fn (str meta "   " content))
                 (callback-fn (str meta "\t" content))
                 content))))

(describe
 "Document preamble"

 (verify-header {:meta        "#+TITLE:"
                 :content     "org-clj is an org-mode parser"
                 :callback-fn parse-title})
 (verify-header {:meta        "#+AUTHOR:"
                 :content     "Yen-Chin, Lee"
                 :callback-fn parse-author})
 (verify-header {:meta        "#+CREATOR:"
                 :content     "coldnew.tw@gmail.com"
                 :callback-fn parse-creator})
 (verify-header {:meta        "#+DATE:"
                 :content     "2015-06-08 21:05:24"
                 :callback-fn parse-date})
 (verify-header {:meta        "#+EMAIL:"
                 :content     "coldnew.tw@gmail.com"
                 :callback-fn parse-email})
 (verify-header {:meta        "#+LANGUAGE:"
                 :content     "zh-tw"
                 :callback-fn parse-language})
 (verify-header {:meta        "#+SELECT_TAGS:"
                 :content     "todo"
                 :callback-fn parse-select-tags})
 (verify-header {:meta        "#+EXCLUDE_TAGS:"
                 :content     "todo"
                 :callback-fn parse-exclude-tags})
 )

(defn verify-tree [file ast]
  (it (str "Verify org-mode file with AST tree\n" file)
      (should (= (parse-org file) ast))))


(describe
 "Document preamble to AST"

 (verify-tree (slurp-resource "test1.org")
              {:title "org-clj 簡易測試"
               :author "Yen-Chin, Lee"
               :email "coldnew.tw@gmail.com"
               :language "zh-tw"
               })
 )

;; Excute the spec
(run-specs)