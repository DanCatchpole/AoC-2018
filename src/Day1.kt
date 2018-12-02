import java.io.File
import kotlin.system.measureTimeMillis

class Day1 {

    companion object {
        fun partA() : Int = File("day1.txt").readLines().map { it.toInt() }.sum()

        fun partB() : Int {
            var currentFreq = 0
            val prevFreqs: MutableSet<Int> = mutableSetOf()
            val lines = File("day1.txt").readLines()
            while (true) {
                for (line in lines) {
                    currentFreq += line.toInt()
                    if (prevFreqs.contains(currentFreq)) {
                        return currentFreq
                    }
                    prevFreqs.add(currentFreq)
                }
            }
        }
    }
}

fun main(args : Array<String>) {
    var aAns = 0
    val aTime = measureTimeMillis { aAns = Day1.partA() }
    println("Part A: $aAns in ${aTime}ms")

    var bAns = 0
    val bTime = measureTimeMillis { bAns = Day1.partB() }
    println("Part B: $bAns in $bTime ms")
}
