import extensions.containsRepeatedChar
import extensions.firstRepeatedChar
import extensions.md5

class KeyGenerator(private val salt: String) {

  companion object {
    private val NUMBER_OF_HASHES_TO_LOOK_AHEAD = 1000
  }

  fun generateKeys(numberOfKeys: Int, applyKeyStretching: Boolean) : List<Int> {
    val result = mutableSetOf<Int>()
    val keyCandidates = mutableMapOf<Int, Char>()

    var currentIndex = 0
    var largestIndexToCheck = Integer.MAX_VALUE

    while (currentIndex < largestIndexToCheck) {
      var currentHash = "$salt$currentIndex".md5()

      if (applyKeyStretching) {
        for (i in 1..2016) {
          currentHash = currentHash.md5()
        }
      }

      val keyCandidateEntriesIterator = keyCandidates.iterator()

      while (keyCandidateEntriesIterator.hasNext()) {
        val keyCandidateEntry = keyCandidateEntriesIterator.next()

        if (keyCandidateEntry.key + NUMBER_OF_HASHES_TO_LOOK_AHEAD < currentIndex) {
          keyCandidateEntriesIterator.remove()
        } else if (currentHash.containsRepeatedChar(keyCandidateEntry.value, minRepetitions = 5)) {
          result.add(keyCandidateEntry.key)

          /*
           * Once we've found the requested number of keys, check NUMBER_OF_HASHES_TO_LOOK_AHEAD more candidates in case
           * there are smaller keys that we have yet to discover.
           */
          if (result.size == numberOfKeys && largestIndexToCheck == Integer.MAX_VALUE) {
            largestIndexToCheck = currentIndex + NUMBER_OF_HASHES_TO_LOOK_AHEAD
          }

          keyCandidateEntriesIterator.remove()
        }
      }

      val firstCharRepeatedThreeTimes = currentHash.firstRepeatedChar(minRepetitions = 3)

      firstCharRepeatedThreeTimes?.let {
        keyCandidates.put(currentIndex, it)
      }

      currentIndex += 1
    }

    return result.take(numberOfKeys).sorted()
  }

}
