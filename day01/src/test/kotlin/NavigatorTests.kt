import RotationDirection.LEFT
import RotationDirection.RIGHT
import io.kotlintest.specs.BehaviorSpec

class NavigatorTests : BehaviorSpec() {

  init {
    Given("a Navigator") {

      When("I enter the instructions \"RIGHT, 2, LEFT, 3\"") {
        Then("I should end up 5 blocks away from my starting point") {
          val instructions = listOf(
              Instruction(RIGHT, 2),
              Instruction(LEFT,  3)
          )

          Navigator().computeDistanceToFinalLocation(instructions) shouldBe 5
        }
      }

      When("I enter the instructions \"RIGHT, 2, RIGHT, 2, RIGHT, 2\"") {
        Then("I should end up 2 blocks away from my starting point") {
          val instructions = listOf(
              Instruction(RIGHT, 2),
              Instruction(RIGHT, 2),
              Instruction(RIGHT, 2)
          )

          Navigator().computeDistanceToFinalLocation(instructions) shouldBe 2
        }
      }

      When("I enter the instructions \"RIGHT, 5, LEFT, 5, RIGHT, 5, RIGHT, 3\"") {
        Then("I should end up 12 blocks away from my starting point") {
          val instructions = listOf(
              Instruction(RIGHT, 5),
              Instruction(LEFT,  5),
              Instruction(RIGHT, 5),
              Instruction(RIGHT, 3)
          )

          Navigator().computeDistanceToFinalLocation(instructions) shouldBe 12
        }
      }

      When("I enter the instructions \"RIGHT, 8, RIGHT, 4, RIGHT, 4, RIGHT, 8\"") {
        Then("the first location visited twice should be 4 blocks from my starting point") {
          val instructions = listOf(
              Instruction(RIGHT, 8),
              Instruction(RIGHT, 4),
              Instruction(RIGHT, 4),
              Instruction(RIGHT, 8)
          )

          Navigator().computeDistanceToFirstLocationVisitedTwice(instructions) shouldBe 4
        }
      }

    }
  }

}
