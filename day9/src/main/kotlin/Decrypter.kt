import java.lang.Math.min

data class Decrypter(private val encryptedString: String) {

  companion object {
    private val MARKER_REGEX = Regex("\\((\\d+)x(\\d+)\\)")
  }

  fun decryptUsingFormat1(): String {
    var latestMarker: MatchResult? = MARKER_REGEX.find(encryptedString) ?: return encryptedString

    var result = ""
    var remainingStringToDecrypt = encryptedString

    while (latestMarker != null) {
      result += remainingStringToDecrypt.substring(0 until latestMarker.range.first)

      val firstIndexOfRangeToRepeat = latestMarker.range.last + 1
      val targetLengthOfRangeToRepeat = latestMarker.groupValues[1].toInt()
      val actualLengthOfRangeToRepeat = min(targetLengthOfRangeToRepeat, remainingStringToDecrypt.lastIndex)
      val lastIndexOfRangeToRepeat = firstIndexOfRangeToRepeat + actualLengthOfRangeToRepeat - 1

      val stringToRepeat = remainingStringToDecrypt.substring(firstIndexOfRangeToRepeat..lastIndexOfRangeToRepeat)
      result += stringToRepeat.repeat(latestMarker.groupValues[2].toInt())

      remainingStringToDecrypt = remainingStringToDecrypt.removeRange(0..lastIndexOfRangeToRepeat)

      latestMarker = MARKER_REGEX.find(remainingStringToDecrypt)
    }

    result += remainingStringToDecrypt
    return result
  }

  fun lengthWhenDecryptedUsingFormat1(): Int {
    return decryptUsingFormat1().length
  }

  fun lengthWhenDecryptedUsingFormat2(): Int {
    return 0
  }

}