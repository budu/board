(ns board.core
  (:require [clojure.browser.event :as event]
            [clojure.browser.dom :as dom]))

(defn log [obj]
  (.log js/console obj))

(defn draw []
  (when-let [board (dom/get-element :board)]
    (doto (.getContext board "2d")
      (.fillRect 10 10 100 100))))

(draw)
