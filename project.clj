(defproject rss-fetcher "0.1.0"
  :description "A RSS fetcher CLI program written by Clojure, for my practice"
  :url "https:/github.com/haruki7049/rss-fetcher.clj"
  :dependencies [[org.clojure/clojure "1.12.3"]
                 [org.clojure/tools.cli "1.2.245"]]
  :main rss-fetcher.main
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
