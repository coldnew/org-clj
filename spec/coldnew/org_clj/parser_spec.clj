(ns coldnew.org-clj.parser-spec
  (:require [speclj.core :refer :all]
            [speclj.run.standard]
            [coldnew.org-clj.parser :refer :all]))

(defn verify-header
  [{:keys [meta content callback-fn]}]
  (it (str meta " parser")
      (should (= (callback-fn (str meta content))
                 (callback-fn (str meta content "\n"))
                 (callback-fn (str meta content "\n\n"))
                 (callback-fn (str meta " " content))
                 (callback-fn (str meta "  " content))
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
 (verify-header {:meta        "#+EMAIL:"
                 :content     "coldnew.tw@gmail.com"
                 :callback-fn parse-email})
 )
