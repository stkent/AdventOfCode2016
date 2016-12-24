class DuctMap(private val rows: List<String>) {

  companion object {
    private val WALL_CHARACTER = '#'
  }

  val pointsOfInterest: Set<Int>
    get() = poiLocations.keys

  private val poiLocations = mutableMapOf<Int, Location>()

  init {
    for (rowNumber in 0..rows.lastIndex) {
      for (columnNumber in 0..rows[rowNumber].lastIndex) {
        try {
          poiLocations.put(
              key = rows[rowNumber][columnNumber].toString().toInt(),
              value = Location(columnNumber, rowNumber))

        } catch (ignored: NumberFormatException) {}
      }
    }
  }

  fun getPOILocation(poi: Int): Location {
    return poiLocations[poi] ?: throw IllegalStateException("Point of interest not found!")
  }

  fun getAdjacentSpaces(location: Location): Set<Location> {
    val result = mutableSetOf<Location>()

    if (location.x > 0) {
      result.add(location.copy(x = location.x - 1))
    }

    if (location.y > 0) {
      result.add(location.copy(y = location.y - 1))
    }

    result.add(location.copy(x = location.x + 1))
    result.add(location.copy(y = location.y + 1))

    return result.filter { isSpace(it) }.toSet()
  }

  private fun isSpace(location: Location): Boolean {
    return rows[location.y][location.x] != WALL_CHARACTER
  }

}
