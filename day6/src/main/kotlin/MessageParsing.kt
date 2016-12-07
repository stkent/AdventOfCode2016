import java.util.*
import kotlin.comparisons.compareBy
import kotlin.comparisons.compareByDescending
import kotlin.comparisons.thenBy

data class MessageParser(val receivedMessages: List<String>) {

  fun parseUsingHighestFrequencyCharacters(): String {
    val characterFrequencyCollection = mutableListOf<OffsetChar>()

    receivedMessages.forEach { receivedMessage ->
      (0 until receivedMessage.length).mapTo(characterFrequencyCollection) {
        OffsetChar(it, receivedMessage[it])
      }
    }

    return characterFrequencyCollection
        .groupBy { it.offset }
        .mapValues { it.value.map(OffsetChar::char) }
        .mapValues { it.value.getHighestFrequencyElement(Comparator(Char::compareTo)) }
        .map { it.value }
        .joinToString(separator = "")
  }

  fun parseUsingLowestFrequencyCharacters(): String {
    val characterFrequencyCollection = mutableListOf<OffsetChar>()

    receivedMessages.forEach { receivedMessage ->
      (0 until receivedMessage.length).mapTo(characterFrequencyCollection) {
        OffsetChar(it, receivedMessage[it])
      }
    }

    return characterFrequencyCollection
        .groupBy { it.offset }
        .mapValues { it.value.map(OffsetChar::char) }
        .mapValues { it.value.getLowestFrequencyElement(Comparator(Char::compareTo)) }
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

// todo: reduce duplication
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

data class OffsetChar(val offset: Int, val char: Char)
