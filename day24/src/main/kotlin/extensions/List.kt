package extensions

fun <T> List<T>.toChainedPairs(): List<Pair<T, T>> {
  if (size < 2) throw IllegalStateException("toChainedPairs may only be called on lists of length 2 or greater.")

  return (0..lastIndex - 1).map { Pair(get(it), get(it + 1)) }
}
