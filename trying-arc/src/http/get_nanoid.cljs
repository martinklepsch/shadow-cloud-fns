(ns http.get-nanoid
  (:require ["nanoid" :as nanoid]))

(def exports
  (clj->js
   {"handler" (fn http [_req _context callback]
                (callback
                 nil
                 #js {:statusCode 200
                      :body (str "nanoid: " (nanoid/nanoid))}))}))
