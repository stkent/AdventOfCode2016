data class Node(val x: Int, val y: Int, val size: Int, val used: Int, val avail: Int, val usedPercent: Int) {

  companion object {

    private val SPLIT_REGEX = Regex("\\s+")

    private val FULL_NODE_ADDRESS_REGEX = Regex("/dev/grid/node-x(\\d+)-y(\\d+)")

    // Converts a string of the form "/dev/grid/node-x0-y17    90T   73T    17T   81%" into a Node object.
    fun parse(string: String): Node {
      val stringParts = string.split(SPLIT_REGEX)

      val fullNodeAddressMatchResult = FULL_NODE_ADDRESS_REGEX.matchEntire(stringParts.first())!!
      return Node(
          x = fullNodeAddressMatchResult.groupValues[1].toInt(),
          y = fullNodeAddressMatchResult.groupValues[2].toInt(),
          size = stringParts[1].dropLast(1).toInt(),
          used = stringParts[2].dropLast(1).toInt(),
          avail = stringParts[3].dropLast(1).toInt(),
          usedPercent = stringParts[4].dropLast(1).toInt()
      )
    }
  }

  override fun toString(): String {
    return "/dev/grid/node-x$x-y$y(size=${size}T, used=${used}T, avail=${avail}T, usedPercent=$usedPercent%)"
  }

}
