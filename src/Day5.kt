import java.io.File
import java.lang.Math.max
import kotlin.system.measureTimeMillis

class Day5 {

    companion object {

        private fun remove(line: String, start : Int = 0) : Pair<String, Int> {
            val updatedLine: String
            for (i in start until line.length-1) {
                val currentChar = line[i]
                val nextChar = line[i+1]
                if (currentChar.isUpperCase() && nextChar.isLowerCase() && currentChar == nextChar.toUpperCase()) {
                    updatedLine = line.substring(0, i) + line.substring(i+2, line.length)
                    return Pair(updatedLine, max(i-1, 0))
                }

                if (currentChar.isLowerCase() && nextChar.isUpperCase() && currentChar == nextChar.toLowerCase()) {
                    updatedLine = line.substring(0, i) + line.substring(i+2, line.length)
                    return Pair(updatedLine, max(i-1, 0))
                }
            }
            return Pair(line, 0)
        }

        fun partA(line: String) : String {
            var currentStr = line
            var currentPos = 0
            while (true) {
                val (nextString, pos) = remove(currentStr, currentPos)
                if (currentStr == nextString) {
                    return nextString
                } else {
                    currentStr = nextString
                    currentPos = pos
                }
            }
        }


        fun partB(input: String) : Int {
            var shortestLen = input.length
            for (c in 'a'..'z') {
                val current = input.replace(c.toString(), "", true)
                val len = partA(current).length
                if (len < shortestLen) shortestLen = len
            }
            return shortestLen
        }
    }
}

fun main(args : Array<String>) {
    val input = File("day5.txt").readLines()[0]

    var aAns = ""
    val aTime = measureTimeMillis { aAns = Day5.partA(input) }
    println("Part A: ${aAns.length} in ${aTime}ms")

    var bAns = 0
    val bTime = measureTimeMillis { bAns = Day5.partB(input) }
    println("Part B: $bAns in $bTime ms")

}
