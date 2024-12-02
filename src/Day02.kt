import kotlin.math.abs
import kotlin.math.sign

fun main() {

    val minGap = 1
    val maxGap = 3

    fun safe(report: List<Long>): Boolean {
        if (report.size <= 1) return true

        val sign = (report[1] - report[0]).sign
        val allPairs = report.zip(report.slice(1..<report.size))
        return allPairs.all { (a, b) ->
            (b - a).sign == sign
                    && minGap <= abs(b - a)
                    && abs(b - a) <= maxGap
        }
    }

    fun safeWithModule(report: List<Long>): Boolean {
        return safe(report) || IntRange(0, report.size).any { id -> safe(report.filterIndexed { i, _ -> i != id }) }
    }

    val listOfLines = readInput("day2Input")
    val safeReports = listOfLines.map {
        it.split("\\s+".toRegex()).map { s -> s.toLong() }
    }.count { safe(it) }

    safeReports.println()

    val safeReportsExtended = listOfLines.map {
        it.split("\\s+".toRegex()).map { s -> s.toLong() }
    }.count { safeWithModule(it) }

    safeReportsExtended.println()
}


