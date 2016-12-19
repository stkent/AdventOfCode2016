package extensions

fun <K> Iterable<K>.countsByKey(): Map<K, Int> {
  return groupBy({ it }).mapValues { it.value.size }
}
