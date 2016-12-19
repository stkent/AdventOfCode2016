import Element.*

class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
//      val initialState = FacilityState.newInstance(
//          floors = sortedMapOf(
//              Pair(4, setOf()),
//              Pair(3, setOf(Generator(Lithium))),
//              Pair(2, setOf(Generator(Hydrogen))),
//              Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
//          ),
//          elevatorFloorNumber = 1,
//          stepsFromInitialState = 0
//      )
//
//      val targetState = FacilityState.newInstance(
//          floors = sortedMapOf(
//              Pair(4, setOf(Chip(Hydrogen), Chip(Lithium), Generator(Hydrogen), Generator(Lithium))),
//              Pair(3, setOf()),
//              Pair(2, setOf()),
//              Pair(1, setOf())
//          ),
//          elevatorFloorNumber = 4
//      )

      val initialState = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(4, setOf()),
              Pair(3, setOf(Generator(Promethium), Chip(Promethium), Generator(Ruthenium), Chip(Ruthenium))),
              Pair(2, setOf(Chip(Plutonium), Chip(Strontium))),
              Pair(1, setOf(Generator(Thulium), Chip(Thulium), Generator(Plutonium), Generator(Strontium)))
          ),
          elevatorFloorNumber = 1,
          stepsFromInitialState = 0
      )

      val targetState = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(4, setOf(Generator(Promethium), Chip(Promethium), Generator(Ruthenium), Chip(Ruthenium), Chip(Plutonium), Chip(Strontium), Generator(Thulium), Chip(Thulium), Generator(Plutonium), Generator(Strontium))),
              Pair(3, setOf()),
              Pair(2, setOf()),
              Pair(1, setOf())
          ),
          elevatorFloorNumber = 4
      )

      val visitedFacilityStates = mutableListOf<FacilityState>()
      val facilityStatesToVisit = mutableListOf(initialState)
      var trackedStepNumber = 0

      while (facilityStatesToVisit.isNotEmpty()) {
        val currentFacilityState = facilityStatesToVisit.first()
        val currentStepNumber = currentFacilityState.stepsFromInitialState!!

        if (currentFacilityState == targetState) {
          println("Part 1: $currentStepNumber")
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
  }

}
