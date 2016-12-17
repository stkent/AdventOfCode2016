import Door.*
import extensions.md5
import java.util.*

class Router(val salt: String) {

  companion object {
    val gridWidth = 4
    val gridHeight = 4
    val startLocation = Location(0, 0)
    val vaultLocation = Location(3, 3)

    private fun getOpenDoors(currentLocation: Location, hash: String): Set<Door> {
      if (hash.length < 4) {
        throw IllegalStateException("Input must contain at least 4 characters.")
      }

      val availableDoors = getAvailableDoors(currentLocation).toMutableSet()

      val result = availableDoors
          .filter { availableDoor -> "bcdef".contains(hash[availableDoor.ordinal]) }
          .toSet()

      return result
    }

    private fun getAvailableDoors(currentLocation: Location): Set<Door> {
      val result = emptySet<Door>().toMutableSet()

      if (currentLocation.x > 0) result.add(LEFT)
      if (currentLocation.y > 0) result.add(UP)
      if (currentLocation.x < gridWidth - 1) result.add(RIGHT)
      if (currentLocation.y < gridHeight - 1) result.add(DOWN)

      return result
    }
  }

  fun shortestRouteToVault(): String {
    val pathsToProcess = mutableListOf(Path(startLocation))

    while (pathsToProcess.isNotEmpty()) {
      val pathToProcess = pathsToProcess.first()
      val currentLocation = pathToProcess.lastLocation

      if (currentLocation == vaultLocation) return pathToProcess.toString()

      pathsToProcess.addAll(getExtendedPaths(pathToProcess))
      pathsToProcess.remove(pathToProcess)
    }

    throw IllegalStateException("Impossible to reach vault location from start location!")
  }

  fun lengthOfLongestRouteToVault(): Int? {
    val pathsToProcess = mutableListOf(Path(startLocation))
    var longestPathDiscovered: Path? = null

    while (pathsToProcess.isNotEmpty()) {
      val pathToProcess = pathsToProcess.first()
      val currentLocation = pathToProcess.lastLocation

      if (currentLocation == vaultLocation) {
        longestPathDiscovered = pathToProcess
      } else {
        pathsToProcess.addAll(getExtendedPaths(pathToProcess))
      }

      pathsToProcess.remove(pathToProcess)
    }

    return longestPathDiscovered?.length
  }

  private fun getExtendedPaths(currentPath: Path): Set<Path> {
    val result = mutableSetOf<Path>()

    val hash = "$salt$currentPath".md5()
    val currentPathDoorSequence = currentPath.doorSequence

    getOpenDoors(currentPath.lastLocation, hash).forEach { door ->
      val updatedPathDoorSequence = ArrayList(currentPathDoorSequence)
      updatedPathDoorSequence.add(door)
      result.add(currentPath.copy(doorSequence = updatedPathDoorSequence))
    }

    return result
  }

}
