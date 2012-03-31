(ns board.core
  (:use (lamina [core :exclude [restart]])
        aleph.http
        (ring.middleware resource file-info)
        (hiccup core page)))

(def board-channel (named-channel :board))

(defn board-handler [channel]
  (siphon channel board-channel)
  (siphon board-channel channel))

(defn page []
  (html5
   [:head]
   [:body
    [:canvas#board {:width 1000 :height 800}]
    (include-js "/js/app.js")]))

(defn sync-app [request]
  {:status 200
   :headers {"content-type" "text/html"}
   :body (page)})

(def wrapped-sync-app
  (-> sync-app
      (wrap-resource "public")
      (wrap-file-info)))

(defn app [channel request]
  (if (:websocket request)
    (board-handler channel)
    (enqueue channel (wrapped-sync-app request))))

(defn -main [& args]
  (start-http-server app {:port 8080 :websocket true}))
