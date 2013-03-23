(declare make-step)

(defn solve-problem [input]
	(let [words (clojure.string/split input #"\s")
		  replacementsCount (Integer/parseInt (first words))
		  replacements (take replacementsCount (rest words))
		  disappearsAndTask (drop (+ 1 replacementsCount) words)
		  disappearsCount (Integer/parseInt (first disappearsAndTask))
		  disappears (take disappearsCount disappearsAndTask)
		  taskChars (re-seq #"\w" (last words))
		]

		(str "[" (clojure.string/join ", " taskChars) "]")))

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


(defn solve-problem-suite []
	(with-open [w (clojure.java.io/writer output-file :append false)]
		(doseq [i (range 1 (+ 1 (Integer/parseInt (first input)))) ]
			(let [output-line (format "Case #%d: %s\n" i (solve-problem (nth input i)))]
				(print output-line)
				(.write w output-line)))))

(solve-problem-suite)

