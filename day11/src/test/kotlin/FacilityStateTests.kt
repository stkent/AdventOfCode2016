import Element.Hydrogen
import Element.Lithium
import io.kotlintest.specs.BehaviorSpec

class FacilityStateTests : BehaviorSpec() {

  init {
    Given("") {
      val state = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(4, setOf()),
              Pair(3, setOf(Generator(Lithium))),
              Pair(2, setOf(Generator(Hydrogen))),
              Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
          ),
          elevatorFloorNumber = 4
      )

      When("") {
        val stateIsEquivalentToItself = state.isEquivalentTo(state)

        Then("") {
          stateIsEquivalentToItself shouldBe true
        }
      }
    }

    Given("two facility states with two complementary components each, with component locations switched") {
      val state1 = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(2, setOf(Generator(Hydrogen))),
              Pair(1, setOf(Chip(Hydrogen)))
          ),
          elevatorFloorNumber = 1
      )

      val state2 = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(2, setOf(Chip(Hydrogen))),
              Pair(1, setOf(Generator(Hydrogen)))
          ),
          elevatorFloorNumber = 1
      )

      When("I ask if the two states are equivalent") {
        val statesAreEquivalent = state1.isEquivalentTo(state2)

        Then("the answer should be no") {
          statesAreEquivalent shouldBe false
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          val state1 = FacilityState.newInstance(
              floors = sortedMapOf(
                  Pair(4, setOf()),
                  Pair(3, setOf(Generator(Lithium))),
                  Pair(2, setOf(Generator(Hydrogen))),
                  Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
              ),
              elevatorFloorNumber = 1
          )

          val state2 = FacilityState.newInstance(
              floors = sortedMapOf(
                  Pair(4, setOf()),
                  Pair(3, setOf(Generator(Lithium))),
                  Pair(2, setOf(Generator(Hydrogen))),
                  Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
              ),
              elevatorFloorNumber = 2
          )

          state1.isEquivalentTo(state2) shouldBe false
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          val state1 = FacilityState.newInstance(
              floors = sortedMapOf(
                  Pair(4, setOf()),
                  Pair(3, setOf(Generator(Lithium))),
                  Pair(2, setOf(Generator(Hydrogen))),
                  Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
              ),
              elevatorFloorNumber = 2
          )

          val state2 = FacilityState.newInstance(
              floors = sortedMapOf(
                  Pair(4, setOf(Generator(Lithium))),
                  Pair(3, setOf()),
                  Pair(2, setOf(Generator(Hydrogen))),
                  Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
              ),
              elevatorFloorNumber = 2
          )

          state1.isEquivalentTo(state2) shouldBe false
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          val state1 = FacilityState.newInstance(
              floors = sortedMapOf(
                  Pair(4, setOf()),
                  Pair(3, setOf(Generator(Lithium))),
                  Pair(2, setOf(Generator(Hydrogen))),
                  Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
              ),
              elevatorFloorNumber = 1
          )

          val state2 = FacilityState.newInstance(
              floors = sortedMapOf(
                  Pair(4, setOf()),
                  Pair(3, setOf(Generator(Hydrogen))),
                  Pair(2, setOf(Generator(Lithium))),
                  Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
              ),
              elevatorFloorNumber = 1
          )

          state1.isEquivalentTo(state2) shouldBe true
        }
      }
    }

//    Given("") {
//      When("") {
//        Then("") {
//          val state = FacilityState.newInstance(
//              floors = sortedMapOf(
//                  Pair(4, setOf(Generator(Lithium))),
//                  Pair(3, setOf(Generator(Hydrogen))),
//                  Pair(2, setOf(Chip(Hydrogen), Chip(Lithium))),
//                  Pair(1, setOf())
//              ),
//              elevatorFloorNumber = 1
//          )
//
//          state.getValidNextStates() shouldBe emptySet<FacilityState>()
//        }
//      }
//    }
  }

}