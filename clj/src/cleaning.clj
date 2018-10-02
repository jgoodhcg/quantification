(ns cleaning
  (:require [clojure.java.shell :refer [sh]]))

;; Wyoming docs
;; did this first https://ubuntuforums.org/showthread.php?t=1190887
;; to move to text files
;; then this https://stackoverflow.com/a/12246397/5040125
;; to rename without spaces
(def wyoming-docs-dir (clojure.java.io/file "/media/justin/data/projects/quantification-data/raw-data/wyoming-docs/txt"))
(def wyoming-docs-fs (file-seq wyoming-docs-dir))

(def wyoming-docs-file-names
  (->> wyoming-docs-fs
       (map (fn [f] (.toString (.toPath f))))
       (rest)))

(def date-regex
  #"(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday), (January|Febraury|March|April|May|June|July|August|September|October|November|December) [0-9]{1,2}, [0-9]{4}")

(->> wyoming-docs-file-names
     (first)
     (slurp)
     (clojure.string/split-lines)
     (partition-by #(re-matches date-regex %)))
