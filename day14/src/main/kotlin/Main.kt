import extensions.containsRepeatedChar
import extensions.firstRepeatedChar
import extensions.md5

// todo: refactor
fun main(args: Array<String>) {
  val input = "cuanljph"

  val keys = mutableSetOf<Int>()
  val keyCandidates = mutableMapOf<Int, Char>()

  var currentIndex = 0
  var largestIndexToCheck = Integer.MAX_VALUE

  while (currentIndex < largestIndexToCheck) {
    val hash = "$input$currentIndex".md5()
//    var hash = "$input$currentIndex".md5()

//    for (i in 1..2016) {
//      hash = hash.md5()
//    }

    val keyCandidateEntriesIterator = keyCandidates.iterator()

    while (keyCandidateEntriesIterator.hasNext()) {
      val keyCandidateEntry = keyCandidateEntriesIterator.next()

      if (keyCandidateEntry.key + 1000 < currentIndex) {
        keyCandidateEntriesIterator.remove()
      } else if (hash.containsRepeatedChar(keyCandidateEntry.value, repetitions = 5)) {
        keys.add(keyCandidateEntry.key)

        if (keys.size == 64 && largestIndexToCheck == Integer.MAX_VALUE) {
          largestIndexToCheck = currentIndex + 1000
        }

        keyCandidateEntriesIterator.remove()
      }
    }

    val firstCharRepeatedThreeTimes = hash.firstRepeatedChar(repetitions = 3)

    firstCharRepeatedThreeTimes?.let {
      keyCandidates.put(currentIndex, it)
    }

    currentIndex += 1
  }

  println("Part 1: ${keys.sorted()[63]}")
}
