data class Decrypter(private val encryptedString: String) {

  companion object {
    private val MARKER_REGEX = Regex("\\((\\d+)x(\\d+)\\)")
  }

  fun calculateLengthUsingAlgorithm1(): Int {
    return lengthV1(encryptedString)
  }

  private fun lengthV1(string: String): Int {
    val leftmostMarker = MARKER_REGEX.find(string) ?: return string.length

    val markerStartIndex = leftmostMarker.range.first
    val markerEndIndex = leftmostMarker.range.last
    val numberOfCharsToRepeat = leftmostMarker.groupValues[1].toInt()
    val numberOfRepeats = leftmostMarker.groupValues[2].toInt()

    // Count leading characters (to the left of the processed group).
    return markerStartIndex +
        // Markers _within_ the target marker group are not expanded.
        numberOfCharsToRepeat * numberOfRepeats +
        // Process the remaining string (to the right of the processed group).
        lengthV1(string.substring(markerEndIndex + numberOfCharsToRepeat + 1))
  }

  fun calculateLengthUsingAlgorithm2(): Long {
    return lengthV2(encryptedString)
  }

  private fun lengthV2(string: String): Long {
    val leftmostMarker = MARKER_REGEX.find(string) ?: return string.length.toLong()

    val markerStartIndex = leftmostMarker.range.first
    val markerEndIndex = leftmostMarker.range.last
    val numberOfCharsToRepeat = leftmostMarker.groupValues[1].toInt()
    val numberOfRepeats = leftmostMarker.groupValues[2].toInt()

    // Count leading characters (to the left of the processed group).
    return markerStartIndex +
        // Markers within the target marker group are themselves expanded post-duplication.
        numberOfRepeats * lengthV2(string.substring(markerEndIndex + 1..markerEndIndex + numberOfCharsToRepeat)) +
        // Process the remaining string (to the right of the processed group).
        lengthV2(string.substring(markerEndIndex + numberOfCharsToRepeat + 1))
  }

}
