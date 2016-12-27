import java.util.regex.Pattern
import kotlin.comparisons.compareByDescending
import kotlin.comparisons.thenBy

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
        .toSortedSet(                                // Sort entries by count then character:  [a=2, b=1, c=1, e=1]
            compareByDescending<Map.Entry<Char, Int>> { it.value }
           .thenBy { it.key }
        )
        .map { it.key }                              // Replace full entries by keys:          [a, b, c, e]
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
