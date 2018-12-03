import java.io.File
import kotlin.system.measureTimeMillis

class Day2 {

    companion object {

        fun occurrences(str : String) : Set<Int> = str.toSet().distinct().map { c -> str.count { it == c } }.toSet()


        fun partA() : Int {
            var occur2 = 0
            var occur3 = 0
            File("day2.txt").forEachLine {
                val occurs = occurrences(it)
                if (2 in occurs) occur2++
                if (3 in occurs) occur3++
            }
            return occur2 * occur3
        }

        // qcslyvphgkrmdawljuefotxbh
        fun partB() : String {
            val lines = File("day2.txt").readLines()
            for (line1 in lines) {
                for (line2 in lines) {
                    var sim = line1.length
                    var currString = ""
                    if (line1 == line2) continue
                    for (i in 0 until line1.length) {
                        if (line1[i] == line2[i]) {
                            sim--
                            currString += line1[i]
                        }
                    }
                    if (sim == 1) return currString
                }
            }
            return ""
        }
    }
}

fun main(args : Array<String>) {
    var aAns = 0
    val aTime = measureTimeMillis { aAns = Day2.partA() }
    println("Part A: $aAns in ${aTime}ms")

    var bAns = ""
    val bTime = measureTimeMillis { bAns = Day2.partB() }
    println("Part B: $bAns in $bTime ms")

}
