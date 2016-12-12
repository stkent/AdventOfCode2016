import extensions.powerSet
import java.util.*

data class FacilityState private constructor(
    val floors: SortedMap<Int, Set<Component>>,
    val elevatorFloorNumber: Int,
    val stepsFromInitialState: Int?,
    val parentState: FacilityState?) {

  companion object {
    fun newInstance(
        floors: SortedMap<Int, Set<Component>>,
        elevatorFloorNumber: Int = 0,
        stepsFromInitialState: Int? = null,
        parentState: FacilityState? = null): FacilityState {

      return FacilityState(
          floors = floors,
          elevatorFloorNumber = elevatorFloorNumber,
          stepsFromInitialState = stepsFromInitialState,
          parentState = parentState
      )
    }
  }

  fun setStepsFromInitialState(stepsFromInitialState: Int): FacilityState {
    return copy(stepsFromInitialState = stepsFromInitialState)
  }

  fun setParentState(parentState: FacilityState): FacilityState {
    return copy(parentState = parentState)
  }

  fun getValidNextStates(stepsFromInitialState: Int): Set<FacilityState> {
    val componentCombos = floors[elevatorFloorNumber]!!
        .powerSet()
        .filter { it.isNotEmpty() && it.size <= 2 }

    return getAdjacentFloors(elevatorFloorNumber)
        .intersect(getLowestNonEmptyFloor()..floors.lastKey())
        .map { adjacentFloor ->
          componentCombos.map { components ->
            moveComponents(components, fromFloor = elevatorFloorNumber, toFloor = adjacentFloor)
                .setStepsFromInitialState(stepsFromInitialState)
                .setParentState(this)
          }
        }
        .flatten()
        .filter(FacilityState::isValid)
        .toSet()
  }

  private fun moveComponents(components: Set<Component>, fromFloor: Int, toFloor: Int): FacilityState {
    val updatedFloors = HashMap(floors).toSortedMap()

    updatedFloors[fromFloor] = updatedFloors[fromFloor]!!.toSet() - components
    updatedFloors[toFloor] = updatedFloors[toFloor]!!.toSet() + components

    return copy(
        floors = updatedFloors,
        elevatorFloorNumber = toFloor
    )
  }

  private fun getAdjacentFloors(floor: Int): Set<Int> {
    return when (floor) {
      0 -> setOf(1)
      floors.lastKey() -> setOf(floors.lastKey() - 1)
      else -> setOf(floor + 1, floor - 1)
    }
  }

  private fun getLowestNonEmptyFloor(): Int {
    floors.forEach { floor ->
      if (floor.value.isNotEmpty()) {
        return floor.key
      }
    }

    throw IllegalStateException("This should never be executed!")
  }

  private fun isValid(): Boolean {
    return floors.values.all { components ->
      val floorContainsGenerator = components.any { it is Generator }
      val allChipsPowered = components.all { if (it is Chip) components.contains(it.complementaryGenerator) else true }

      !floorContainsGenerator || allChipsPowered
    }
  }

  override fun toString(): String {
    return floors
        .map { floor -> "F${floor.key} ${if (floor.key == elevatorFloorNumber) { "E" } else { "." }} ${floor.value}" }
        .reversed()
        .joinToString(separator = "\n")
  }

  // Generated implementations.

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other?.javaClass != javaClass) return false

    other as FacilityState

    if (floors != other.floors) return false
    if (elevatorFloorNumber != other.elevatorFloorNumber) return false

    return true
  }

  override fun hashCode(): Int {
    var result = floors.hashCode()
    result = 31 * result + elevatorFloorNumber
    return result
  }

}
