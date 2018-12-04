import java.io.File
import kotlin.system.measureTimeMillis

class Day4 {

    companion object {

        private fun extractGuardNumber(line: String) : Int = line.substringAfter("#").substringBefore(" ").toInt()


        fun partA() : Map<Pair<Int, Int>, Int> {
            val lines = File("day4.txt").readLines().sorted()
            // Guard,Min -> Count asleep then
            val mapGuardToTimes = mutableMapOf<Pair<Int, Int>, Int>()
            val mapGuardToSleepy = mutableMapOf<Int, Int>()
            var currentGuard = 0
            for (i in 0 until lines.size) {
                val line = lines[i]
                if (line.contains("Guard")) {
                    currentGuard = extractGuardNumber(line)
                    continue
                }
                if (line.contains("falls asleep")) {
                    // get next line -> write falling asleep until then
                    val currentTime = line.substringAfter(":").substringBefore("]").toInt()
                    val nextTime = lines[i+1].substringAfter(":").substringBefore("]").toInt()

                    for (t in currentTime until nextTime) {
                        mapGuardToTimes[Pair(currentGuard, t)] = mapGuardToTimes.getOrDefault(Pair(currentGuard, t), 0) + 1
                        mapGuardToSleepy[currentGuard] = mapGuardToSleepy.getOrDefault(currentGuard, 0) + 1
                    }
                }
            }

            val sleepyGuard = mapGuardToSleepy.filter { it.value == mapGuardToSleepy.values.max() }.keys.iterator().next()
            println(sleepyGuard)
            val sleepyGuardTimes = mapGuardToTimes.filter { it.key.first == sleepyGuard }
            return sleepyGuardTimes.filter { it.value ==  sleepyGuardTimes.values.max() }
        }

        // the same but without the added restriction o.o
        fun partB() : Map<Pair<Int, Int>, Int> {
            val lines = File("day4.txt").readLines().sorted()
            // Guard,Min -> Count asleep then
            val mapGuardToTimes = mutableMapOf<Pair<Int, Int>, Int>()
            var currentGuard = 0
            for (i in 0 until lines.size) {
                val line = lines[i]
                if (line.contains("Guard")) {
                    currentGuard = extractGuardNumber(line)
                    continue
                }
                if (line.contains("falls asleep")) {
                    // get next line -> write falling asleep until then
                    val currentTime = line.substringAfter(":").substringBefore("]").toInt()
                    val nextTime = lines[i+1].substringAfter(":").substringBefore("]").toInt()

                    for (t in currentTime until nextTime) {
                        mapGuardToTimes[Pair(currentGuard, t)] = mapGuardToTimes.getOrDefault(Pair(currentGuard, t), 0) + 1
                    }
                }
            }

            return mapGuardToTimes.filter { it.value ==  mapGuardToTimes.values.max() }
        }
    }
}

fun main(args : Array<String>) {
    var aAns = mapOf<Pair<Int, Int>, Int>()
    val aTime = measureTimeMillis { aAns = Day4.partA() }
    println("Part A: $aAns in ${aTime}ms")

    var bAns = mapOf<Pair<Int, Int>, Int>()
    val bTime = measureTimeMillis { bAns = Day4.partB() }
    println("Part B: $bAns in $bTime ms")

}
