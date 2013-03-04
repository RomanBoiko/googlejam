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

(defn number-of-steps [task]
	(Integer/parseInt (subs task 0 1)))

(with-open [w (clojure.java.io/writer output-file :append false)]
	(doseq [i (range 1 (+ 1 (Integer/parseInt (first input)))) ]
		(let [output-line (format "Case#%d: %d\n" i (number-of-steps (nth input i)))]
			(print output-line)
			(.write w output-line))))