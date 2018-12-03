import java.awt.Rectangle
import java.io.File
import kotlin.system.measureTimeMillis

class Day3 {

    companion object {

        private fun getSize(line: String) : List<Int> = line.substringAfter(": ").split("x").map { it.toInt() }
        private fun getPosition(line: String) : List<Int> = line.substringAfter("@ ").substringBefore(":").split(",").map { it.toInt() }

        fun partA() : Int {
            val claims = mutableMapOf<Pair<Int, Int>, Int>()

            val lines = File("day3.txt").readLines()
            for (line in lines) {
                val size = getSize(line)
                val positions = getPosition(line)
                for (i in positions[0] until positions[0] + size[0]) {
                    for (j in positions[1] until positions[1] + size[1]) {
                        claims[Pair(i, j)] = claims.getOrDefault(Pair(i, j), 0) + 1
                    }
                }
            }
            return claims.count { it.value > 1 }
        }

        // 775
        fun partB() : Rectangle {
            val claims = mutableListOf<Rectangle>()
            val lines = File("day3.txt").readLines()
            for (line in lines) {
                val size = getSize(line)
                val positions = getPosition(line)
                claims.add(Rectangle(positions[0], positions[1], size[0], size[1]))
            }

            for (claim1 in claims) {
                var hasCollided = false
                for (claim2 in claims) {
                    if (claim1 == claim2) continue
                    if (claim1.intersects(claim2)) hasCollided = true
                }
                if (!hasCollided) return claim1
            }
            return Rectangle()
        }
    }
}

fun main(args : Array<String>) {
    var aAns = 0
    val aTime = measureTimeMillis { aAns = Day3.partA() }
    println("Part A: $aAns in ${aTime}ms")

    var bAns = Rectangle()
    val bTime = measureTimeMillis { bAns = Day3.partB() }
    println("Part B: $bAns in $bTime ms")

}
