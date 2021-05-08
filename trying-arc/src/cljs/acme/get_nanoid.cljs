(ns acme.get-nanoid
  (:require ["nanoid" :as nanoid]))

(def exports
  (clj->js
   {"handler" (fn http [_req _context callback]
                (callback
                 nil
                 #js {:statusCode 200
                      :body (str "nanoid: " (nanoid/nanoid))}))}))

(def another-exports
  (clj->js
   {"handler" (fn http [_req _context callback]
                (callback
                 nil
                 #js {:statusCode 200
                      :body (str "another-nanoid: " (nanoid/nanoid))}))}))
