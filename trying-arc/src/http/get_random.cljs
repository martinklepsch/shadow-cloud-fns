(ns http.get-random
  (:require ["crypto-random-string" :as gr]))

(def exports
  (clj->js
   {"handler" (fn [_req _context callback]
                ;; somehow broken
                ;; (js/console.log #_(gr (clj->js { :length 10})))
                (callback
                 nil
                 #js {:statusCode 200
                      :body (str "this is random: " (rand-int 999))}))}))
