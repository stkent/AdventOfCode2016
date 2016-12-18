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

  fun getValidNextStates(): Set<FacilityState> {
    val componentCombos = floors[elevatorFloorNumber]!!
        .powerSet()
        .filter { it.isNotEmpty() && it.size <= 2 }

    return getAdjacentFloors(elevatorFloorNumber)
        .intersect(getLowestNonEmptyFloor()..floors.lastKey())
        .map { adjacentFloor ->
          componentCombos.map { components ->
            moveComponents(components, fromFloor = elevatorFloorNumber, toFloor = adjacentFloor)
          }
        }
        .flatten()
        .filter(FacilityState::isValid)
        .toSet()
  }

  fun isEquivalentTo(facilityState: FacilityState): Boolean {
    if (facilityState.elevatorFloorNumber != elevatorFloorNumber) {
      return false
    }

    return facilityState.getElementPairs().size == getElementPairs().size &&
        facilityState.getElementPairs().containsAll(getElementPairs()) &&
        getElementPairs().containsAll(facilityState.getElementPairs())
  }

  private fun getElementPairs(): Collection<Pair<Int, Int>> {
    return floors
        .entries
        .map {
          it.value.map { component -> Pair(component.element, it.key) }
        }
        .flatten()
        .groupBy { it.first }
        .mapValues { it.value.map { it.second } }
        .mapValues { Pair(it.value[0], it.value[1]) }
        .values
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
      floors.firstKey() -> setOf(floor + 1)
      floors.lastKey() -> setOf(floor - 1)
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
      val floorContainsGenerator = components.any { component -> component is Generator }
      val allChipsPowered = components.all { component ->
        if (component is Chip) components.contains(component.complementaryGenerator) else true
      }

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
