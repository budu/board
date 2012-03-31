(defproject board "0.1.0-SNAPSHOT"
  :description "Experimental drawing board"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [aleph "0.2.1-SNAPSHOT"]
                 [ring "1.1.0-beta2"]
                 [hiccup "1.0.0-beta1"]
                 [org.clojure/clojurescript "0.0-993"]]
  :source-path "src/clj"
  :extra-classpath-dirs ["src/cljs"]
  :plugins [[lein-cljsbuild "0.1.4"]]
  :cljsbuild {:builds
              [{:source-path "src/cljs"
                :compiler {:output-to "resources/public/js/app.js"
                           :optimizations :advanced}}]}
  :ring {:handler board.core/app})
