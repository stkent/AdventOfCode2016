import Element.*

fun main(args: Array<String>) {
  // Part 1
  val initialState = FacilityState.newInstance(
      floors = sortedMapOf(
          Pair(4, emptySet()),
          Pair(3, setOf(
              Generator(Lithium))),
          Pair(2, setOf(
              Generator(Hydrogen))),
          Pair(1, setOf(
              Chip(Hydrogen),
              Chip(Lithium)))
      ),
      elevatorFloorNumber = 1,
      stepsFromInitialState = 0
  )

  val targetState = FacilityState.newInstance(
      floors = sortedMapOf(
          Pair(4, setOf(
              Chip(Hydrogen),
              Chip(Lithium),
              Generator(Hydrogen),
              Generator(Lithium))),
          Pair(3, emptySet()),
          Pair(2, emptySet()),
          Pair(1, emptySet())
      ),
      elevatorFloorNumber = 4
  )

//  // Part 2
//  val initialState = FacilityState.newInstance(
//      floors = sortedMapOf(
//          Pair(4, emptySet()),
//          Pair(3, setOf(
//              Generator(Promethium),
//              Chip(Promethium),
//              Generator(Ruthenium),
//              Chip(Ruthenium))),
//          Pair(2, setOf(
//              Chip(Plutonium),
//              Chip(Strontium))),
//          Pair(1, setOf(
//              Generator(Thulium),
//              Chip(Thulium),
//              Generator(Plutonium),
//              Generator(Strontium),
//              Generator(Elerium),
//              Chip(Elerium),
//              Generator(Dilithium),
//              Chip(Dilithium)))
//      ),
//      elevatorFloorNumber = 1,
//      stepsFromInitialState = 0
//  )
//
//  val targetState = FacilityState.newInstance(
//      floors = sortedMapOf(
//          Pair(4, setOf(
//              Generator(Promethium),
//              Chip(Promethium),
//              Generator(Ruthenium),
//              Chip(Ruthenium),
//              Chip(Plutonium),
//              Chip(Strontium),
//              Generator(Thulium),
//              Chip(Thulium),
//              Generator(Plutonium),
//              Generator(Strontium),
//              Generator(Elerium),
//              Chip(Elerium),
//              Generator(Dilithium),
//              Chip(Dilithium))),
//          Pair(3, emptySet()),
//          Pair(2, emptySet()),
//          Pair(1, emptySet())
//      ),
//      elevatorFloorNumber = 4
//  )

  val visitedFacilityStates = mutableListOf<FacilityState>()
  val facilityStatesToVisit = mutableListOf(initialState)
  var trackedStepNumber = 0

  while (facilityStatesToVisit.isNotEmpty()) {
    val currentFacilityState = facilityStatesToVisit.first()
    val currentStepNumber = currentFacilityState.stepsFromInitialState!!

    if (currentFacilityState == targetState) {
      println("Solution: $currentStepNumber steps")
      break
    } else {
      val nextStepNumber = currentStepNumber + 1

      if (currentStepNumber != trackedStepNumber) {
        println("Current step number: $currentStepNumber")
        trackedStepNumber = currentStepNumber
      }

      val newFacilityStatesToVisit =
          currentFacilityState.getValidNextStates()
              .filter { newFacilityState ->
                (visitedFacilityStates union facilityStatesToVisit).none { newFacilityState.isEquivalentTo(it) }
              }
              .map { it.copy(stepsFromInitialState = nextStepNumber) }

      facilityStatesToVisit.addAll(newFacilityStatesToVisit)
      facilityStatesToVisit.remove(currentFacilityState)
      visitedFacilityStates.add(currentFacilityState)
    }
  }
}
