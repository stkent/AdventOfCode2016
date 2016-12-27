import Element.Hydrogen
import Element.Lithium
import io.kotlintest.specs.BehaviorSpec

class FacilityStateTests : BehaviorSpec() {

  init {
    Given("any valid facility state") {
      val state = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(4, emptySet()),
              Pair(3, setOf(Generator(Lithium))),
              Pair(2, setOf(Generator(Hydrogen))),
              Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
          ),
          elevatorFloorNumber = 4
      )

      When("I ask if that state is equivalent to itself") {
        val stateIsEquivalentToItself = state.isEquivalentTo(state)

        Then("the answer should be yes") {
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

    Given("two facility states with identical sets of elements distributed in unequal counts between two floors") {
      val state1 = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(2, setOf(Generator(Hydrogen), Chip(Lithium), Generator(Lithium))),
              Pair(1, setOf(Chip(Hydrogen)))
          ),
          elevatorFloorNumber = 1
      )

      val state2 = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(2, setOf(Chip(Hydrogen))),
              Pair(1, setOf(Generator(Hydrogen), Chip(Lithium), Generator(Lithium)))
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

    Given("two facility states with identical element locations but different elevator floors") {
      val state1 = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(4, emptySet()),
              Pair(3, setOf(Generator(Lithium))),
              Pair(2, setOf(Generator(Hydrogen))),
              Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
          ),
          elevatorFloorNumber = 1
      )

      val state2 = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(4, emptySet()),
              Pair(3, setOf(Generator(Lithium))),
              Pair(2, setOf(Generator(Hydrogen))),
              Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
          ),
          elevatorFloorNumber = 2
      )

      When("I ask if the two states are equivalent") {
        val statesAreEquivalent = state1.isEquivalentTo(state2)

        Then("the answer should be no") {
          statesAreEquivalent shouldBe false
        }
      }
    }

    Given("two facility states with all but one component on identical floors") {
      val state1 = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(4, emptySet()),
              Pair(3, setOf(Generator(Lithium))),
              Pair(2, setOf(Generator(Hydrogen))),
              Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
          ),
          elevatorFloorNumber = 2
      )

      val state2 = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(4, setOf(Generator(Lithium))),
              Pair(3, emptySet()),
              Pair(2, setOf(Generator(Hydrogen))),
              Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
          ),
          elevatorFloorNumber = 2
      )

      When("I ask if the two states are equivalent") {
        val statesAreEquivalent = state1.isEquivalentTo(state2)

        Then("the answer should be no") {
          statesAreEquivalent shouldBe false
        }
      }
    }

    Given("two facility states with four complementary components each, with pairs of components mapped equivalently") {
      val state1 = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(4, emptySet()),
              Pair(3, setOf(Generator(Lithium))),
              Pair(2, setOf(Generator(Hydrogen))),
              Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
          ),
          elevatorFloorNumber = 1
      )

      val state2 = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(4, emptySet()),
              Pair(3, setOf(Generator(Hydrogen))),
              Pair(2, setOf(Generator(Lithium))),
              Pair(1, setOf(Chip(Hydrogen), Chip(Lithium)))
          ),
          elevatorFloorNumber = 1
      )

      When("I ask if the two states are equivalent") {
        val statesAreEquivalent = state1.isEquivalentTo(state2)

        Then("the answer should be yes") {
          statesAreEquivalent shouldBe true
        }
      }
    }

    Given("a facility state with no elements on the first floor and the elevator on the second floor") {
      val state = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(4, setOf(Generator(Lithium))),
              Pair(3, setOf(Generator(Hydrogen))),
              Pair(2, setOf(Chip(Hydrogen), Chip(Lithium))),
              Pair(1, emptySet())
          ),
          elevatorFloorNumber = 2
      )

      When("I compute the list of valid next states") {
        val nextValidStates = state.getValidNextStates()

        Then("the results correctly exclude states that involve moving elements downwards (optimization)") {
          nextValidStates shouldBe setOf(
              FacilityState.newInstance(
                  floors = sortedMapOf(
                      Pair(4, setOf(Generator(Lithium))),
                      Pair(3, setOf(Generator(Hydrogen), Chip(Hydrogen))),
                      Pair(2, setOf(Chip(Lithium))),
                      Pair(1, emptySet())
                  ),
                  elevatorFloorNumber = 3)
          )
        }
      }
    }
  }

}
