
(ns app.comp.sun-demo
  (:require [phlox.core
             :refer
             [defcomp g hslx rect circle text container graphics create-list hslx]]
            [app.comp.reset :refer [comp-reset]]))

(defn generate-line-ops []
  (let [x0 2, ops [(g :move-to [(+ x0 (* 400 (js/Math.random))) 0])]]
    (loop [acc ops, x x0]
      (if (> x 300)
        acc
        (let [x1 (+ x (* 80 (js/Math.random))), x2 (+ x1 (+ 4 (* 8 (js/Math.random))))]
          (recur
           (conj
            acc
            (g
             :line-style
             {:color (* (js/Math.random) (hslx 0 0 100)),
              :width (if (< x2 160) 2 3),
              :alpha (if (< x2 80) 0.2 0.9)})
            (g :line-to [x1 0])
            (g :move-to [x2 0]))
           x2))))))

(defcomp
 comp-sun-demo
 (touch-key)
 (container
  {:position [200 200]}
  (comp-reset [-200 -200])
  (create-list
   :container
   {:position [200 40]}
   (->> (range 200)
        (map
         (fn [x]
           [x
            (graphics
             {:position [0 0], :rotation (* 0.01 js/Math.PI x), :ops (generate-line-ops)})]))))))
