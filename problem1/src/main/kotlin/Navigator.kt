import Orientation.*
import RotationDirection.LEFT
import RotationDirection.RIGHT
import extensions.abs

class Navigator {

  private var orientation = NORTH

  private var position = Pair(0, 0)

  private val distanceFromOrigin: Int
    get() = position.first.abs() + position.second.abs()

  fun computeDistanceToFinalLocation(instructions: List<Instruction>): Int {
    instructions.forEach { instruction ->
      updateOrientation(instruction.rotationDirection)
      updatePosition(instruction.distanceToAdvance)
    }

    return distanceFromOrigin
  }

  fun computeDistanceToFirstLocationVisitedTwice(instructions: List<Instruction>): Int {
    val visitedLocations: MutableSet<Pair<Int, Int>> = mutableSetOf()

    instructions.forEach { instruction ->
      updateOrientation(instruction.rotationDirection)

      var remainingDistanceToAdvance = instruction.distanceToAdvance

      while (remainingDistanceToAdvance > 0) {
        updatePosition(distanceToAdvance = 1)

        if (visitedLocations.contains(position)) {
          return distanceFromOrigin
        } else {
          visitedLocations.add(position.copy())
          remainingDistanceToAdvance -= 1
        }
      }
    }

    throw IllegalArgumentException("No location is visited more than once!")
  }

  private fun updateOrientation(rotationDirection: RotationDirection) {
    when (rotationDirection) {
      LEFT  -> orientation = orientation.turnLeft()
      RIGHT -> orientation = orientation.turnRight()
    }
  }

  private fun updatePosition(distanceToAdvance: Int) {
    when (orientation) {
      NORTH -> position = position.copy(second = position.second + distanceToAdvance)
      EAST  -> position = position.copy(first  = position.first  + distanceToAdvance)
      SOUTH -> position = position.copy(second = position.second - distanceToAdvance)
      WEST  -> position = position.copy(first  = position.first  - distanceToAdvance)
    }
  }

}
