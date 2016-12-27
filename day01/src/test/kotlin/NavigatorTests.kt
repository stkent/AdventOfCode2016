import RotationDirection.LEFT
import RotationDirection.RIGHT
import io.kotlintest.specs.BehaviorSpec

class NavigatorTests : BehaviorSpec() {

  init {
    Given("a Navigator") {
      val navigator = Navigator()

      When("I follow the instructions \"RIGHT, 2, LEFT, 3\"") {
        val instructions = listOf(
            Instruction(RIGHT, 2),
            Instruction(LEFT, 3)
        )

        val distanceTraveled = navigator.computeDistanceToFinalLocation(instructions)

        Then("I should end up 5 blocks away from my starting point") {
          distanceTraveled shouldBe 5
        }
      }
    }

    Given("a Navigator") {
      val navigator = Navigator()

      When("I follow the instructions \"RIGHT, 2, RIGHT, 2, RIGHT, 2\"") {
        val instructions = listOf(
            Instruction(RIGHT, 2),
            Instruction(RIGHT, 2),
            Instruction(RIGHT, 2)
        )

        val distanceTraveled = navigator.computeDistanceToFinalLocation(instructions)

        Then("I should end up 2 blocks away from my starting point") {
          distanceTraveled shouldBe 2
        }
      }
    }

    Given("a Navigator") {
      val navigator = Navigator()

      When("I follow the instructions \"RIGHT, 5, LEFT, 5, RIGHT, 5, RIGHT, 3\"") {
        val instructions = listOf(
            Instruction(RIGHT, 5),
            Instruction(LEFT, 5),
            Instruction(RIGHT, 5),
            Instruction(RIGHT, 3)
        )

        val distanceTraveled = navigator.computeDistanceToFinalLocation(instructions)

        Then("I should end up 12 blocks away from my starting point") {
           distanceTraveled shouldBe 12
        }
      }
    }

    Given("a Navigator") {
      val navigator = Navigator()

      When("I follow the instructions \"RIGHT, 8, RIGHT, 4, RIGHT, 4, RIGHT, 8\"") {
        val instructions = listOf(
            Instruction(RIGHT, 8),
            Instruction(RIGHT, 4),
            Instruction(RIGHT, 4),
            Instruction(RIGHT, 8)
        )

        val distanceToFirstLocationVisitedTwice = navigator.computeDistanceToFirstLocationVisitedTwice(instructions)

        Then("the first location visited twice should be 4 blocks from my starting point") {
          distanceToFirstLocationVisitedTwice shouldBe 4
        }
      }
    }
  }

}
