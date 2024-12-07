fun main() {
    val input = readInput("day5input")

    var sum = 0L

    val leftToRightMapping = input
        .takeWhile { it != "" }
        .map { it.split("|") }
        .groupBy({ it[0].toInt() }) { it[1].toInt() }
    leftToRightMapping.println()
    val secondPart = input.takeLastWhile { it != "" }

    val invalidRows = mutableListOf<List<Int>>()

    secondPart.map {
        it.split(",").map { n -> n.toInt() }
    }.forEach { row ->
        var valid = true
        for (i in row.indices) {
            for (j in i + 1..<row.size) {
                if (row[j] !in leftToRightMapping.getOrDefault(row[i], listOf())) {
                    valid = false
                }
            }
        }
        if (valid) {
            sum += row[row.size / 2]
        } else {
            invalidRows.add(row)
        }
    }

    sum.println()

    val sumOfInvalid = invalidRows.map {
        it.sortedWith { a, b ->
            when {
                leftToRightMapping[a]?.contains(b) == true -> -1
                leftToRightMapping[b]?.contains(a) == true -> 1
                else -> 0
            }
        }
    }.sumOf {
        it[it.size / 2]
    }

    sumOfInvalid.println()


}