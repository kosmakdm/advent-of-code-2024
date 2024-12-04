fun main() {
    val input = readInput("day4input")

    val xmas = "XMAS"
    val samx = "SAMX"

    var count = 0

    fun registerWord(word: String, ): Int {
        var c = 0
        if (word == xmas) {
            c++
        }
        if (word == samx) {
            c++
        }
        return c
    }

    for (i in input.indices) {
        for (j in input[0].indices) {
            if (j < input[0].length - 3) {
                val word = input[i].slice(j..j + 3)
                count += registerWord(word)
            }
            if (i < input.size - 3) {
                val word = input[i][j].toString() + input[i + 1][j] + input[i + 2][j] + input[i + 3][j]
                count += registerWord(word)
            }
            if (i < input.size - 3 && j < input[0].length - 3) {
                val word = input[i][j].toString() + input[i + 1][j + 1] + input[i + 2][j + 2] + input[i + 3][j + 3]
                count += registerWord(word)
            }
            if (i < input.size - 3 && j > 2) {
                val word = input[i][j].toString() + input[i + 1][j - 1] + input[i + 2][j - 2] + input[i + 3][j - 3]
                count += registerWord(word)
            }
        }
    }

    count.println()

    val mas = "MAS"
    val sam = "SAM"

    var countX = 0

    for (i in input.indices) {
        for (j in input[0].indices) {
            if (i < input.size - 2 && j < input[0].length - 2) {
                val cross1 = input[i][j].toString() + input[i + 1][j + 1] + input[i + 2][j + 2]
                val cross2 = input[i + 2][j].toString() + input[i + 1][j + 1] + input[i][j + 2]
                if ((cross1 == mas || cross1 == sam) && (cross2 == mas || cross2 == sam)) {
                    countX++
                }
            }
        }
    }

    countX.println()


}