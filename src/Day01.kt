import kotlin.math.abs

fun main() {

    val listOfLines = readInput("day1Input")
    val listOfPairs = listOfLines.map {
        val (left, right) = it.split("\\s+".toRegex())
        left.toLong() to right.toLong()
    }

    val sortedFirst = listOfPairs.map { it.first }.sorted()
    val sortedSecond = listOfPairs.map { it.second }.sorted()

    val sum = sortedFirst.zip(sortedSecond).sumOf {
        abs(it.first - it.second)
    }

    sum.println()

    val secondAsMap = sortedSecond
        .groupingBy{ it }
        .eachCount()

    val similarity = sortedFirst.sumOf {
        val similarityMultiplier = secondAsMap.getOrDefault(it, 0)
        similarityMultiplier * it
    }

    similarity.println()

}
