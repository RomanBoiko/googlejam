;Not the version to work with huge numbers. the last one is using BigDecimal

(defn isPalindrom [candidate]
	(= (str candidate) (clojure.string/reverse (str candidate))))

(defn isFairAndSquareRoot [root]
	(and (isPalindrom root) (isPalindrom (* root root))))

(defn solve-problem [input]
	(let [ 	limits (clojure.string/split input #"\s")
			lowerLimit (Long/parseLong (first limits))
			higherLimit (Long/parseLong (nth limits 1))
			lowerRoot (Math/round (Math/ceil (Math/sqrt lowerLimit)))
			higherRoot (Math/round (Math/floor (Math/sqrt higherLimit)))
		]
		(count (filter isFairAndSquareRoot (range lowerRoot (+ higherRoot 1))))))



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
			(let [output-line (format "Case #%d: %d\n" i (solve-problem (nth input i)))]
				(print output-line)
				(.write w output-line)))))

(solve-problem-suite)

