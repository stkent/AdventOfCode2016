import java.util.*
import java.util.regex.Pattern

class RoomCandidate(identifyingString: String) {
  
  companion object {
    private val aAsInt = 'a'.toInt()
  }

  val sectorId: Int
  
  private val checkSum: String
  
  private val encryptedName: String

  init {
    sectorId = identifyingString.filter(Char::isDigit).toInt()

    val checksumMatcher = Pattern.compile("\\[(\\w+)\\]").matcher(identifyingString)
    checksumMatcher.find()
    checkSum = checksumMatcher.group(1)
    
    encryptedName = identifyingString.removeSuffix("-$sectorId[$checkSum]")
  }

  fun isARealRoom(): Boolean {
    val fiveMostFrequentCharacters = encryptedName   // Initial encrypted name:                "ba-ec-a"
        .filter(Char::isLetter)                      // Remove dashes:                         "baeca"
        .groupBy { it }                              // Group characters by character:         {b=[b], a=[a, a], e=[e], c=[c]}
        .mapValues { it.value.size }                 // Convert values from lists to lengths:  {b=1, a=2, e=1, c=1}
        .entries                                     // Convert map to a set of entries:       [b=1, a=2, e=1, c=1]
        .groupBy({ it.value }, { it.key })           // Group by value; old keys = new values: {1: [b, e, c], 2: [a]}
        .toSortedMap(Comparator { v1, v2 -> v2-v1 }) // Sort by key (highest count first):     {2: [a], 1: [b, e, c]}
        .mapValues { it.value.sorted() }             // Sort values individually:              {2: [a], 1: [b, c, e]}
        .flatMap{ it.value }                         // Concatenate values:                    [a, b, c, e]
        .take(5)                                     // Take at most 5 characters:             [a, b, c, e]
        .joinToString(separator = "")                // Form a single string:                  "abce"
    
    return checkSum == fiveMostFrequentCharacters
  }

  /**
   * Note: this method is only expected to return a human-readable String if isARealRoom returns true. 
   */
  fun getDecryptedRoomName(): String {
    val shift = sectorId % 26
    
    return encryptedName.map { char ->
      when (char) {
        '-' -> ' '
        else -> (aAsInt + ((char.toInt() - aAsInt) + shift) % 26).toChar()
      }
    }.joinToString(separator = "")
  }

}
