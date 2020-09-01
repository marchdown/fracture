(ns fracture.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com]
   [fracture.subs :as subs]
   [fracture.db :as db]
   [fracture.events :as events]
   [fracture.routes :as routes] ;;! this helps! why?
   [fracture.components.compsteps.markup :as markup-step]
   ))

(defn title []
  (let [name (re-frame/subscribe [::subs/name])]
    [re-com/title
     :style {:color "red" :background "navy"}
     :label (str "Hello from " @name)
     :level :level1]))


(defn steps [current-route-key]
  (->> (list [::routes/landing "Introduction" "Show very basic container"]
             ;; [::routes/layout "Layout containers" "Use containers to create reusable layouts"]
             ;; [::routes/params "Parametrized" "Use function arguments to create specific versions of a container"]
             ;; [::routes/stateful "Stateful" "Build tabs component using containers"]
             ;; )
       (map (fn [[route-key title desc]]
              [:a.step
               {:class    (when (= current-route-key route-key) "active")
                :on-click #(re-frame/dispatch [::events/change-route-key route-key])}
               [:div.content
                [:div.title title]
                [:div.description
                 {:style {:max-width "10em"}}
                 desc]]]))

       (into [:div.ui.vertical.ordered.steps]))))




(defn comp-panel []
  (let [route-key @(re-frame/subscribe [::subs/current-route-key])]
    [:div.ui.container
     {:style {:margin-top "5em"}}
     [:h1.ui.header "Using Containers with Reagent and re-frame"]
     [:div.ui.grid
      [:div.two.column.row
       [:div.four.wide.column
        [steps route-key]]
       [:div.twelve.wide.column
        ^{:key route-key}
        [:div.section
         (condp = route-key
           ::routes/layout [markup-step/grid-container-panel]
           ;; ::routes/params [params-step/main-panel]
           ;; ::routes/stateful [stateful-step/main-panel]
           [markup-step/basic-container-panel])]]]]]))




;;; snip
(defn main-panel []
  [re-com/v-box
   :height "80%"
   :children [[title]
              ]])
