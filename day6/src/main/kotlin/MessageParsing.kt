import java.util.*
import kotlin.comparisons.compareBy
import kotlin.comparisons.compareByDescending
import kotlin.comparisons.thenBy

data class MessageParser(val receivedMessages: List<String>) {

  fun parseUsingHighestFrequencyCharacters(): String {
    return parse { candidateChars -> candidateChars.getHighestFrequencyElement(Comparator(Char::compareTo)) }
  }

  fun parseUsingLowestFrequencyCharacters(): String {
    return parse { candidateChars -> candidateChars.getLowestFrequencyElement(Comparator(Char::compareTo)) }
  }

  private fun parse(realCharacterIdentifier: (List<Char>) -> Char): String {
    val characterFrequencyCollection = mutableListOf<IndexedChar>()

    receivedMessages.forEach { receivedMessage ->
      (0 until receivedMessage.length).mapTo(characterFrequencyCollection) {
        IndexedChar(it, receivedMessage[it])
      }
    }

    return characterFrequencyCollection
        .groupBy { it.index }
        .mapValues { it.value.map(IndexedChar::char) }
        .mapValues { realCharacterIdentifier(it.value) }
        .map { it.value }
        .joinToString(separator = "")
  }

}

fun <T : Comparable<T>> Collection<T>.getHighestFrequencyElement(tieBreaker: Comparator<T>): T {
  return this.groupBy { it }
      .mapValues { it.value.size }
      .entries
      .toSortedSet(
          compareByDescending(selector = Map.Entry<T, Int>::value)
         .thenBy(selector = Map.Entry<T, Int>::key, comparator = tieBreaker)
      )
      .first()
      .key
}

fun <T : Comparable<T>> Collection<T>.getLowestFrequencyElement(tieBreaker: Comparator<T>): T {
  return this.groupBy { it }
      .mapValues { it.value.size }
      .entries
      .toSortedSet(
          compareBy(selector = Map.Entry<T, Int>::value)
         .thenBy(selector = Map.Entry<T, Int>::key, comparator = tieBreaker)
      )
      .first()
      .key
}

private data class IndexedChar(val index: Int, val char: Char)
