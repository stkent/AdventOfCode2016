import Element.*
import com.sun.tools.javac.jvm.Gen
import extensions.powerSet

class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
//      val initialState = FacilityState.newInstance(
//          floors = sortedMapOf(
//              Pair(3, setOf()),
//              Pair(2, setOf(Generator(Lithium))),
//              Pair(1, setOf(Generator(Hydrogen))),
//              Pair(0, setOf(Chip(Hydrogen), Chip(Lithium)))
//          ),
//          elevatorFloorNumber = 0,
//          stepsFromInitialState = 0
//      )
//
//      val targetState = FacilityState.newInstance(
//          floors = sortedMapOf(
//              Pair(3, setOf(Chip(Hydrogen), Chip(Lithium), Generator(Hydrogen), Generator(Lithium))),
//              Pair(2, setOf()),
//              Pair(1, setOf()),
//              Pair(0, setOf())
//          ),
//          elevatorFloorNumber = 3
//      )

      val initialState = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(3, setOf()),
              Pair(2, setOf(Generator(Promethium), Chip(Promethium), Generator(Ruthenium), Chip(Ruthenium))),
              Pair(1, setOf(Chip(Plutonium), Chip(Strontium))),
              Pair(0, setOf(Generator(Thulium), Chip(Thulium), Generator(Plutonium), Generator(Strontium)))
          ),
          elevatorFloorNumber = 0,
          stepsFromInitialState = 0
      )

      val targetState = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(3, setOf(Generator(Promethium), Chip(Promethium), Generator(Ruthenium), Chip(Ruthenium), Chip(Plutonium), Chip(Strontium), Generator(Thulium), Chip(Thulium), Generator(Plutonium), Generator(Strontium))),
              Pair(2, setOf()),
              Pair(1, setOf()),
              Pair(0, setOf())
          ),
          elevatorFloorNumber = 3
      )

      val facilityStatesToVisit = mutableSetOf(initialState)

      var currentSearchDepth = 0

      while (facilityStatesToVisit.isNotEmpty()) {
        val currentFacilityState = facilityStatesToVisit.first()
        val currentStepNumber = currentFacilityState.stepsFromInitialState!!

        if (currentStepNumber != currentSearchDepth) {
          currentSearchDepth = currentStepNumber
          println("Current search depth: $currentSearchDepth")
        }

        if (currentFacilityState == targetState) {
          println("Part 1: $currentStepNumber")

//          var facilityStateToPrint: FacilityState? = currentFacilityState
//
//          for (i in currentStepNumber downTo 1) {
//            println(facilityStateToPrint!!.parentState)
//            facilityStateToPrint = facilityStateToPrint.parentState
//          }

          break
        } else {
          val nextStepNumber = currentStepNumber + 1

          val newFacilityStatesToVisit = currentFacilityState.getValidNextStates(nextStepNumber) - facilityStatesToVisit

          facilityStatesToVisit.addAll(newFacilityStatesToVisit)
          facilityStatesToVisit.remove(currentFacilityState)
        }
      }
    }
  }

}
