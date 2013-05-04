(ns boikoro (:require [clojure.math.numeric-tower :as math]))

(defn amountOfPaint [radius]
	(- (math/expt (inc radius) 2)  (math/expt radius 2)))

(defn solve-problem [input]
	(let [ numbers (map (fn [x] (Long/parseLong x)) (re-seq #"\w+" input))
		firstRadius (first numbers)
		paint (nth numbers 1)
		paintRequired (- (math/expt (inc firstRadius) 2) (math/expt firstRadius 2))
		]
		(loop [counter 0 paintLeft paint required paintRequired]
				(do
					;(print (format "--left: %s, counter: %s\n" paintLeft counter))
					(if 
						(> required paintLeft)
						counter
						(recur (inc counter) (- paintLeft required) (+ 4 required)))))))


; ========================
; ====INFRASTRUCTURE
; ========================
(defn read-lines [filename]
	(line-seq (clojure.java.io/reader (clojure.java.io/file filename))))

(defn file-name [extension]
	(clojure.string/replace (get (meta #'read-lines) :file) ".clj" extension))

(def input-file (file-name ".in"))

(def output-file (file-name ".out"))

(def input (read-lines input-file))

;;;;;;;;;;;;INPUT: SINGLE LINE
(defn solve-problem-single-line-input []
	(with-open [w (clojure.java.io/writer output-file :append false)]
		(doseq [i (range 1 (+ 1 (Integer/parseInt (first input)))) ]
			(let [output-line (format "Case #%d: %s\n" i (solve-problem (nth input i)))]
				(print output-line)
				(.write w output-line)))))

;;;;;;;;;;;;INPUT: MATRIX
(defn solve-matrix-input [itemsLeft item lines w]
	(when (> itemsLeft 0)
		(let [
				inputLinesNumber (Integer/parseInt (first (clojure.string/split (first lines) #"\s")))
				inputLines (take inputLinesNumber (drop 1 lines))
				output-line (format "Case #%d: %s\n" item (solve-problem inputLines))
			]
			
			(print output-line)
			(.write w output-line)
			(recur (dec itemsLeft) (inc item)  (drop (+ 1 inputLinesNumber) lines)  w))))

(defn solve-problem-matrix-input []
	(with-open [w (clojure.java.io/writer output-file :append false)]
		(solve-matrix-input (Integer/parseInt (first input)) 1 (rest input) w)))

;;;;;;;;;;;;INPUT: FIXED NUMBER OF LINES
(defn lineByTestCase [lines testCase lineNumber]
	(nth lines (+ testCase (* 4 (- testCase 1)) lineNumber)))

(defn solve-problem-fixed-lines-input []
	(with-open [w (clojure.java.io/writer output-file :append false)]
		(doseq [i (range 1 (+ 1 (Integer/parseInt (first input)))) ]
			(let [output-line (format "Case #%d: %s\n" i (solve-problem
				(lineByTestCase input i 0) (lineByTestCase input i 1) (lineByTestCase input i 2) (lineByTestCase input i 3)
				))]
				(print output-line)
				(.write w output-line)))))

(solve-problem-single-line-input)
