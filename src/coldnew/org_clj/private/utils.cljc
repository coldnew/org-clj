(ns coldnew.org-clj.private.utils
  #?(:cljs (:require-macros
            [coldnew.org-clj.private.utils :refer [slurp-resource]])))

#?(:clj
   (defmacro slurp-resource [file]
     (clojure.core/slurp (clojure.java.io/resource file))))
