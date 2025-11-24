(ns rss-fetcher.main
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.string :as str]
            [dev.dirs ProjectDirectories])
  (:gen-class))

(def cli-options
  ;; An option with an argument
  [["-p" "--port PORT" "Port number"
    :default 80
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
   ;; A non-idempotent option (:default is applied first)
   ["-v" nil "Verbosity level"
    :id :verbosity
    :default 0
    :update-fn inc] ; Prior to 0.4.1, you would have to use:
                   ;; :assoc-fn (fn [m k _] (update-in m [k] inc))
   ;; A boolean option defaulting to nil
   ["-h" "--help"]])

(defn usage [options-summary]
  (->> ["A CLI program to fetch RSS feeds."
        ""
        "Usage: lein run [options] urls"
        ""
        "Options:"
        options-summary
        ""]
       (str/join \newline)))

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]

    ; Handle help option first
    (when (:help options)
      (println (usage summary))
      (System/exit 0))

    ; Handle errors
    (when errors
      (println "ERRORS(S):" errors)
      (System/exit 1))

    ; Main application logic
    (println "Options:")
    (prn options) ; Print parsed options map
    (println "")
    (println "Arguments:")
    (prn arguments) ; Print remaining arguments list

    (System/exit 0)))
