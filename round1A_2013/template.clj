;;external open-source libraries:
;- math.numeric-tower-0.0.2.jar(https://github.com/clojure/math.numeric-tower)
;- clojure-contrib-1.2.0.jar(https://github.com/clojure/clojure-contrib)
;- algotools-0.1.0.jar(https://github.com/vishk/algotools)
(ns boikoro (:require [clojure.math.numeric-tower :as math] [algotools.algos.graph :as g]))

(defn math-examples []
	(+ (math/ceil (math/sqrt (BigInteger. "1000000000000000000000000000000000000000000000000")))
	   (math/floor (math/sqrt (BigInteger. "1000000000000000000000000000000000000000000000000")))
	   (math/expt 10000000000000000000000000000000000 2)
	   (math/abs -243)
	   (math/ceil (BigDecimal. "222222222222222222222222222222222222222222222222222222222222222222222222.2"))))


(defn solve-problem [input]
	(do
		(print (g/scc-directed-graph {3 [9], 9 [6], 6 [3], 1 [6 7], 7 [4], 4 [1], 2 [4 5], 5 [8], 8 [2]}))
		(math-examples)))


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
