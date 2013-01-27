(defproject board "0.1.0-SNAPSHOT"
  :description "Experimental drawing board"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [aleph "0.2.1-beta2"]
                 [ring "1.1.0-beta2"]
                 [hiccup "1.0.0-beta1"]
                 [org.clojure/clojurescript "0.0-1006"]]
  :source-paths ["src/clj"]
  :extra-classpath-dirs ["src/cljs"]
  :plugins [[lein-cljsbuild "0.1.5"]]
  :cljsbuild {:builds
              [{:source-path "src/cljs"
                :compiler {:output-to "resources/public/js/app.js"
                           :optimizations :advanced}}]}
  :ring {:handler board.core/app}
  :main board.core)
