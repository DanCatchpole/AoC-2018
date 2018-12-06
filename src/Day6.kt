import java.io.File
import java.lang.Math.abs
import kotlin.system.measureTimeMillis

class Day6 {

    companion object {

        fun getBounds(points: MutableSet<Pair<Int, Int>>) : Array<Int> {
            val maxX = points.maxBy { it.first }!!.first
            val maxY = points.maxBy { it.second }!!.second
            val minX = points.minBy { it.first }!!.first
            val minY = points.minBy { it.second }!!.second
            return arrayOf(minX, maxX, minY, maxY)
        }

        fun getPoints(input: List<String>) : MutableSet<Pair<Int, Int>> {
            val points = mutableSetOf<Pair<Int,Int>>()
            for (line in input) {
                val vals = line.split(", ")
                points.add(Pair(vals[0].toInt(), vals[1].toInt()))
            }
            return points
        }

        fun getClosest(point: Pair<Int, Int>, possibles: Set<Pair<Int, Int>>) : Pair<Int, Int> {
            val mapp = possibles.map { pp ->
                pp to abs(point.first - pp.first) + abs(point.second - pp.second)
            }.toMap()

            val minDist = mapp.values.min()
            if (mapp.values.count{ it == minDist } > 1) return Pair(-1, -1)

            return mapp.minBy { it.value }!!.key
        }

        fun partA(input: List<String>) : Int {
            val points = getPoints(input)
            val closestPoints = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
            val bounds = getBounds(points)

            for (x in bounds[0]..bounds[1]) {
                for (y in bounds[2] .. bounds[3]) {
                    val point = Pair(x, y)
                    closestPoints[point] = getClosest(point, points)
                }
            }

            closestPoints.filter { it.key.first == bounds[0] || it.key.first == bounds[1] || it.key.second == bounds[2] || it.key.second == bounds[3] }.forEach{
                if (points.contains(it.value)) points.remove(it.value)
            }
            return points.map { possible -> closestPoints.count { it.value == possible } }.max()!!
        }

        fun partB(input: List<String>) : Int {
            val points = getPoints(input)
            val bounds = getBounds(points)
            var count = 0

            for (x in bounds[0]..bounds[1]) {
                for (y in bounds[2]..bounds[3]) {
                    var currDist = 0
                    for (pp in points) {
                        currDist += abs(x - pp.first) + abs(y - pp.second)
                    }
                    if (currDist < 10000) count++
                }
            }
            return count
        }
    }
}

fun main(args : Array<String>) {
    val input = File("day6.txt").readLines()

    var aAns = 0
    val aTime = measureTimeMillis { aAns = Day6.partA(input) }
    println("Part A: $aAns in ${aTime}ms")

    var bAns = 0
    val bTime = measureTimeMillis { bAns = Day6.partB(input) }
    println("Part B: $bAns in $bTime ms")

}
