(defn canBeProcessed [row, columns]
	(let [maxInRow (apply max row)]
		(empty?
		(for [x (range (count row))
			:let [y (nth row x)]
			:when (and (< y maxInRow) (< y (apply max (nth columns x))))]
		1))))

(defn listOfListsOfStringsToInts [rows]
	(reduce (fn [startList listOfIntegersAsStrings] 
				(conj startList 
					(map (fn [x] (Integer/parseInt x)) listOfIntegersAsStrings)))
	[] rows))

(defn solve-problem [inputLines]
	(let [	rows (listOfListsOfStringsToInts (map (fn [x] (re-seq #"\w+" x)) inputLines))
			columnsAsRows (apply mapv vector rows)
			height (count inputLines)
			allHeights (distinct (sort (reduce concat rows)))]
	(loop [rowsToProcess rows]
		(if (not (canBeProcessed (first rowsToProcess) columnsAsRows))
			"NO"
			(if (empty? (rest rowsToProcess))
				"YES"
				(recur (rest rowsToProcess)))))))

; ========================
; ====INFRASTRUCTURE
; ========================
(defn read-lines [filename]
	(line-seq (clojure.java.io/reader (clojure.java.io/file filename))))

(defn file-name [extension]
	(clojure.string/replace
		(get (meta #'read-lines) :file)
		".clj"
		extension))

(def input-file (file-name ".in"))

(def output-file (file-name ".out"))

(def input (read-lines input-file))

(defn lineByTestCase [lines testCase lineNumber]
	(nth lines (+ testCase (* 4 (- testCase 1)) lineNumber)))

(defn solve-task [itemsLeft item lines w]
	(when (> itemsLeft 0)
		(let [
				inputLinesNumber (Integer/parseInt (first (clojure.string/split (first lines) #"\s")))
				inputLines (take inputLinesNumber (drop 1 lines))
				output-line (format "Case #%d: %s\n" item (solve-problem inputLines))
			]
			
			(print output-line)
			(.write w output-line)
			(recur (dec itemsLeft) (inc item)  (drop (+ 1 inputLinesNumber) lines)  w))))

(defn solve-problem-suite []
	(with-open [w (clojure.java.io/writer output-file :append false)]
		(solve-task (Integer/parseInt (first input)) 1 (rest input) w)))

(solve-problem-suite)

