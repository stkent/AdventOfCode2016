class Room(private val firstRowTileTypes: String) {

  companion object {
    private val SAFE = '.'
    private val TRAP = '^'
  }

  fun computeNumberOfSafeTiles(numberOfRows: Int): Int {
    val rows = mutableListOf(firstRowTileTypes)

    while (rows.size < numberOfRows) {
      rows.add(computeNextRow(rows.last()))
    }

    return rows
        .map { row -> row.count { tile -> tile == SAFE } }
        .sum()
  }

  private fun computeNextRow(previousRow: String): String {
    val lastIndex = previousRow.lastIndex

    return (0..lastIndex)
        .map { index ->
          when (index) {
            0         -> "$SAFE${previousRow[0]}${previousRow[1]}"
            lastIndex -> "${previousRow[lastIndex - 1]}${previousRow[lastIndex]}$SAFE"
            else      -> "${previousRow[index - 1]}${previousRow[index]}${previousRow[index + 1]}"
          }
        }
        .map { parentTiles -> computeNewTileType(parentTiles) }
        .joinToString(separator = "")
  }

  private fun computeNewTileType(parentTiles: String): Char {
    return when (parentTiles) {
      "$TRAP$TRAP$SAFE" -> TRAP
      "$SAFE$TRAP$TRAP" -> TRAP
      "$TRAP$SAFE$SAFE" -> TRAP
      "$SAFE$SAFE$TRAP" -> TRAP
      else -> SAFE
    }
  }

}
