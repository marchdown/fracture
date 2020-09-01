(ns fracture.subs
  (:require
   [re-frame.core :as re-frame]
   [fracture.db :as db]
   ))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))


;; comp-panel
(re-frame/reg-sub
  ::current-route-key
  (fn [db]
    (::db/current-route-key db)))
