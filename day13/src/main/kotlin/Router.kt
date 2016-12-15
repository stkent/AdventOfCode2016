import kotlin.comparisons.compareBy
import kotlin.comparisons.thenBy

class Router(val spacePredicate: (Location) -> Boolean) {

  fun lengthOfShortestRouteBetween(startLocation: Location, targetLocation: Location): Int {
    if (!spacePredicate(startLocation)) {
      throw IllegalArgumentException("Start location is not a space!")
    }

    if (!spacePredicate(targetLocation)) {
      throw IllegalArgumentException("Target location is not a space!")
    }

    val visitedLocations = mutableSetOf<Location>()
    val locationsToProcess = mutableMapOf(Pair(startLocation, 0))
    val heuristic: (Location) -> Int = { it.taxicabDistanceTo(targetLocation) }

    while (locationsToProcess.isNotEmpty()) {
      val locationEntryToProcess =
          locationsToProcess
              .entries
              .toSortedSet(
                  compareBy<Map.Entry<Location, Int>>(selector = { it.value + heuristic(it.key) })
                      .thenBy(selector = { heuristic(it.key) })
              )
              .first()

      val locationToProcess = locationEntryToProcess.key
      val distanceToLocationToProcess = locationEntryToProcess.value

      if (locationToProcess.taxicabDistanceTo(targetLocation) == 0) {
        return distanceToLocationToProcess
      }

      if (!visitedLocations.contains(locationToProcess)) {
        visitedLocations.add(locationToProcess)
        locationsToProcess.remove(locationToProcess)

        getAdjacentSpaces(locationToProcess)
            .filter { !visitedLocations.contains(it) }
            .forEach {
              val shortestDistanceToNode = Math.min(
                  distanceToLocationToProcess + 1,
                  locationsToProcess[it] ?: Integer.MAX_VALUE)

              locationsToProcess.put(it, shortestDistanceToNode)
            }
      }
    }

    throw IllegalStateException("Impossible to reach target location from start location!")
  }

  fun findNumberOfSpacesWithinNumberOfSteps(numberOfSteps: Int, startLocation: Location): Int {
    if (!spacePredicate(startLocation)) {
      throw IllegalArgumentException("Start location is not a space!")
    }

    val visitedLocations = mutableListOf<Location>()
    val locationsToProcess = mutableMapOf(Pair(startLocation, 0))

    while (locationsToProcess.filter { it.value <= numberOfSteps }.isNotEmpty()) {
      val locationEntryToProcess = locationsToProcess.entries.first()
      val locationToProcess = locationEntryToProcess.key
      val distanceToLocationToProcess = locationEntryToProcess.value

      visitedLocations.add(locationToProcess)
      locationsToProcess.remove(locationToProcess)

      getAdjacentSpaces(locationToProcess)
          .filter { !visitedLocations.contains(it) }
          .forEach {
            locationsToProcess.put(it, distanceToLocationToProcess + 1)
          }
    }

    return visitedLocations.size
  }

  fun print(xMax: Int, yMax: Int) {
    (0..yMax).forEach { y ->
      (0..xMax)
          .map { Location(it, y) }
          .forEach {
            print(
                if (spacePredicate(it)) {
                  '_'
                } else {
                  '\u2588'
                }
            )
          }

      print("\n")
    }
  }

  private fun getAdjacentSpaces(location: Location): Set<Location> {
    val result = mutableSetOf<Location>()

    if (location.x > 0) {
      result.add(location.copy(x = location.x - 1))
    }

    if (location.y > 0) {
      result.add(location.copy(y = location.y - 1))
    }

    result.add(location.copy(x = location.x + 1))
    result.add(location.copy(y = location.y + 1))

    return result.filter { spacePredicate(it) }.toSet()
  }

}
