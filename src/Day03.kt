fun main() {
    val mulPattern = """mul\((\d{1,3}),(\d{1,3})\)"""
    val doRegex = """do\(\)"""
    val dontRegex = """don't\(\)"""

    val dos = "do()"
    val donts = "don't()"

    val input = readInput("day3input").joinToString(separator = "\n")
    val sum = mulPattern.toRegex().findAll(input)
        .sumOf { match -> match.groupValues[1].toLong() * match.groupValues[2].toLong() }

    sum.println()

    var enabled = true
    var sumAllowed = 0L
    "$mulPattern|$doRegex|$dontRegex".toRegex().findAll(input).forEach { match ->
        when (match.value) {
            dos -> enabled = true
            donts -> enabled = false
            else -> if (enabled) sumAllowed += match.groupValues[1].toLong() * match.groupValues[2].toLong()
        }
    }

    sumAllowed.println()
}


