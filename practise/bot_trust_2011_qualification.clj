(declare make-step)

(defn solve-problem [input]
	(loop [stepsToDo (map #(clojure.string/split % #"\s") (re-seq #"\w\s\d+" input))
		   positions {\O 1 \B 1 \T 0}]
		(if (.isEmpty stepsToDo)
			(positions \T)
		(recur (rest stepsToDo) (make-step stepsToDo positions)))))

(defn positionFromStep [step]
	(Integer/parseInt (nth step 1)))

(defn opponent [toBot]
	(if (= toBot \B) \O \B))

(defn targetOpponentPosition [bot botCurrentPosition stepsToGo]
	(if (.isEmpty stepsToGo)
		botCurrentPosition
		(if (= (first (first (first stepsToGo))) bot)
			(positionFromStep (first stepsToGo))
			(targetOpponentPosition bot botCurrentPosition (rest stepsToGo)))))

(defn difff [pos1 pos2]
	(Math/abs (- pos1 pos2)))

(defn nextPositionAfterDiff [currentPos, targetPos, diff]
	(let [diff12 (difff currentPos targetPos)]
	(if (or (= diff12 0) (< diff12 diff))
		targetPos
		(if (< currentPos targetPos)
			(+ currentPos diff)
			(- currentPos diff)))))

(defn make-step [steps, positions]
	(let [target (first steps)
		  currentBot (first (first target))
		  currentPosition (positions currentBot)
		  targetPosition (positionFromStep target)
		  diff (+ 1 (difff targetPosition currentPosition))

		  opponentBot (opponent currentBot)
		  opponentCurrentPosition (positions opponentBot)
		  opponentTargetPosition (targetOpponentPosition opponentBot opponentCurrentPosition (rest steps))
		  opponentNextPosition (nextPositionAfterDiff opponentCurrentPosition, opponentTargetPosition, diff)
		  ]
		(conj positions {\T (+ diff (positions \T)) currentBot targetPosition opponentBot opponentNextPosition})))

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

