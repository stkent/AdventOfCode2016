import extensions.permutations
import extensions.toChainedPairs
import kotlin.comparisons.compareBy
import kotlin.comparisons.thenBy

class Router(private val ductMap: DuctMap) {

  fun computeLengthOfShortestRoutePassingThroughAllPOIs(mustReturnToInitialPOI: Boolean): Int {
    val pois = ductMap.pointsOfInterest
    val minPOI = pois.min()!!
    val maxPOI = pois.max()!!

    val poiTripLengths = mutableMapOf<Pair<Int, Int>, Int>()

    for (startPOI in minPOI..maxPOI) {
      for (targetPOI in startPOI + 1..(if (startPOI == maxPOI) maxPOI - 1 else maxPOI)) {
        val tripLength = lengthOfShortestRouteBetweenPOIs(startPOI, targetPOI)

        poiTripLengths.put(key = Pair(startPOI, targetPOI), value = tripLength)
        poiTripLengths.put(key = Pair(targetPOI, startPOI), value = tripLength)
      }
    }

    return pois.permutations()
        .filter { permutation -> permutation.first() == 0 }
        .map { permutation ->
          if (mustReturnToInitialPOI) {
            val updatedPermutation = permutation.toMutableList()
            updatedPermutation.add(minPOI)
            updatedPermutation
          } else {
            permutation
          }
        }
        .map { permutation -> permutation.toChainedPairs() }
        .map { chainedPairs -> chainedPairs.map { chainedPair -> poiTripLengths[chainedPair]!! }.sum() }
        .min()!!
  }

  private fun lengthOfShortestRouteBetweenPOIs(startPOI: Int, targetPOI: Int): Int {
    val startLocation = ductMap.getPOILocation(startPOI)
    val targetLocation = ductMap.getPOILocation(targetPOI)

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

        ductMap.getAdjacentSpaces(locationToProcess)
            .filter { !visitedLocations.contains(it) }
            .forEach {
              val shortestDistanceToNode = Math.min(
                  distanceToLocationToProcess + 1,
                  locationsToProcess[it] ?: Integer.MAX_VALUE)

              locationsToProcess.put(it, shortestDistanceToNode)
            }
      }
    }

    throw IllegalStateException(
        "Impossible to reach POI $targetPOI $targetLocation from POI $startPOI $startLocation!")
  }

}
