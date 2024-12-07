fun main() {

    data class Coord(val i: Int, val j: Int) {
        operator fun plus(other: Coord): Coord {
            return Coord(this.i + other.i, this.j + other.j)
        }
    }

    data class DirectedCoord(val position: Coord, val direction: Coord)

    data class Guard(var position: Coord, var direction: Coord) {

        private val tilesVisited = mutableSetOf<Coord>()
        private val loopDetection = mutableSetOf(DirectedCoord(position, direction))
        var tilesCovered = 0
        var inALoop = false

        init {
            tilesVisited.add(position)
        }

        fun rotate90() {
            direction = Coord(direction.j, -direction.i)
        }

        fun nextCoord(): Coord {
            return position + direction
        }

        fun move() {
            position += direction
            if (!tilesVisited.contains(position)) {
                tilesCovered++
                tilesVisited.add(position)
            }
            val directedCoord = DirectedCoord(position, direction)
            if (loopDetection.contains(directedCoord)) {
                inALoop = true
            } else {
                loopDetection.add(directedCoord)
            }
        }

        fun isInside(n: Int, m: Int): Boolean {
            return position.i in 0..<n && position.j in 0..<m
        }
    }

    val input = readInput("day6input")

    val map = input.map { it.toCharArray().toList() }

    val (n, m) = map.size to map[0].size

    val obstacleCoords =
        map.flatMapIndexed { i, chars ->
                chars.mapIndexed { j, c -> c to Coord(i, j) }
                    .filter { it.first == '#' } }
            .map { it.second }
            .toSet()

    val initialDirections = mapOf(
        '^' to Coord(-1, 0),
        '>' to Coord(0, 1),
        'v' to Coord(1, 0),
        '<' to Coord(0, -1)
    )

    var guardInit: Guard? = null

    map.forEachIndexed{i, row -> row.forEachIndexed { j, c ->
        if (initialDirections.containsKey(c)) {
            guardInit = Guard(Coord(i, j), initialDirections[c]!!)
        }
    }}

    val guard = guardInit!!
    val guardCopy = guard.copy()

    val potentialObstacles = map.indices
        .flatMap { i -> map[0].indices.map { j -> Coord(i, j) } }
        .filter { !obstacleCoords.contains(it) && guard.position != it }

    while (guard.isInside(n, m)) {
        if (obstacleCoords.contains(guard.nextCoord())) {
            guard.rotate90()
        } else {
            guard.move()
        }
    }

    guard.tilesCovered.println()

    var inLoopCount = 0

    potentialObstacles.forEach { potentialObstacle ->
        val newObstacles = obstacleCoords + potentialObstacle
        val newGuard = guardCopy.copy()
        while (newGuard.isInside(n, m) && !newGuard.inALoop) {
            if (newObstacles.contains(newGuard.nextCoord())) {
                newGuard.rotate90()
            } else {
                newGuard.move()
            }
        }

        newGuard.tilesCovered.println()
        if (newGuard.inALoop) {
            inLoopCount++
        }
    }

    inLoopCount.println()

}