package extensions

fun <E> Set<E>.permutations(): Set<List<E>> {
  if (isEmpty()) return emptySet()

  if (size == 1) return setOf(listOf(first()))

  val result = mutableSetOf<List<E>>()

  for (element in this) {
    (this - element).permutations().forEach {
      val resultList = mutableListOf(element)
      resultList.addAll(it)
      result.add(resultList)
    }
  }

  return result
}
