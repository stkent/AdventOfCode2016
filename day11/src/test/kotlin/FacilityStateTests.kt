import Element.Hydrogen
import Element.Lithium
import io.kotlintest.specs.BehaviorSpec

class FacilityStateTests : BehaviorSpec() {

  init {
    Given("") {
      val state = FacilityState.newInstance(
          floors = sortedMapOf(
              Pair(3, setOf()),
              Pair(2, setOf(Generator(Lithium))),
              Pair(1, setOf(Generator(Hydrogen))),
              Pair(0, setOf(Chip(Hydrogen), Chip(Lithium)))
          ),
          elevatorFloorNumber = 3
      )

      When("") {
        val stateIsEquivalentToItself = state.isEquivalentTo(state)

        Then("") {
          stateIsEquivalentToItself shouldBe true
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          val state1 = FacilityState.newInstance(
              floors = sortedMapOf(
                  Pair(3, setOf()),
                  Pair(2, setOf(Generator(Lithium))),
                  Pair(1, setOf(Generator(Hydrogen))),
                  Pair(0, setOf(Chip(Hydrogen), Chip(Lithium)))
              ),
              elevatorFloorNumber = 0
          )

          val state2 = FacilityState.newInstance(
              floors = sortedMapOf(
                  Pair(3, setOf()),
                  Pair(2, setOf(Generator(Lithium))),
                  Pair(1, setOf(Generator(Hydrogen))),
                  Pair(0, setOf(Chip(Hydrogen), Chip(Lithium)))
              ),
              elevatorFloorNumber = 1
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
                  Pair(3, setOf()),
                  Pair(2, setOf(Generator(Lithium))),
                  Pair(1, setOf(Generator(Hydrogen))),
                  Pair(0, setOf(Chip(Hydrogen), Chip(Lithium)))
              ),
              elevatorFloorNumber = 2
          )

          val state2 = FacilityState.newInstance(
              floors = sortedMapOf(
                  Pair(3, setOf(Generator(Lithium))),
                  Pair(2, setOf()),
                  Pair(1, setOf(Generator(Hydrogen))),
                  Pair(0, setOf(Chip(Hydrogen), Chip(Lithium)))
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
                  Pair(3, setOf()),
                  Pair(2, setOf(Generator(Lithium))),
                  Pair(1, setOf(Generator(Hydrogen))),
                  Pair(0, setOf(Chip(Hydrogen), Chip(Lithium)))
              ),
              elevatorFloorNumber = 1
          )

          val state2 = FacilityState.newInstance(
              floors = sortedMapOf(
                  Pair(3, setOf()),
                  Pair(2, setOf(Generator(Hydrogen))),
                  Pair(1, setOf(Generator(Lithium))),
                  Pair(0, setOf(Chip(Hydrogen), Chip(Lithium)))
              ),
              elevatorFloorNumber = 1
          )

          state1.isEquivalentTo(state2) shouldBe true
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          val state = FacilityState.newInstance(
              floors = sortedMapOf(
                  Pair(3, setOf(Generator(Lithium))),
                  Pair(2, setOf(Generator(Hydrogen))),
                  Pair(1, setOf(Chip(Hydrogen), Chip(Lithium))),
                  Pair(0, setOf())
              ),
              elevatorFloorNumber = 1
          )

          state.getValidNextStates() shouldBe emptySet<FacilityState>()
        }
      }
    }
  }

}