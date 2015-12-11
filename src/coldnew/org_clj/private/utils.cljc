(ns coldnew.org-clj.private.utils
  #?(:cljs (:require-macros [coldnew.org-clj.private.utils :refer [slurp-resource]])))

#?(:clj
   (defmacro slurp-resource
     "Load resource file, return file contents. For clojurescript, the resource
  is load at compile time. Keep in mind that this is just a macro and will eval
  at compile time, you should not take this as a real function."
     [file]
     (clojure.core/slurp (clojure.java.io/resource file))))