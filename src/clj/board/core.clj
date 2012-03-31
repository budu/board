(ns board.core
  (:use (lamina [core :only [enqueue]])
        aleph.http
        (ring.middleware resource file-info)
        (hiccup core page)))

(defn page []
  (html5
   [:head]
   [:body
    [:p "OK"]
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
    nil ;; TODO: fun stuff
    (enqueue channel (wrapped-sync-app request))))

(defonce stopper (atom nil))

(defn start []
  (when-not @stopper
    (reset! stopper
            (start-http-server app
                               {:port 8080
                                :websocket true}))))

(defn stop []
  (when @stopper
    (@stopper)
    (reset! stopper nil)))

(defn restart []
  (stop)
  (start))
