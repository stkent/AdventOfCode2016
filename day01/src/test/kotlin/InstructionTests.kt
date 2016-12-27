import RotationDirection.LEFT
import RotationDirection.RIGHT
import io.kotlintest.specs.BehaviorSpec

class InstructionTests : BehaviorSpec() {

  init {
    Given("the instruction String \"R1\"") {
      When("I parse that String into an Instruction") {
        Then("the Instruction should expose the correct RotationDirection and distance to advance") {
          val instruction = Instruction("R1")

          instruction shouldBe Instruction(RIGHT, 1)
        }
      }
    }

    Given("the instruction String \"L1\"") {
      When("I parse that String into an Instruction") {
        Then("the Instruction should expose the correct RotationDirection and distance to advance") {
          val instruction = Instruction("L1")

          instruction shouldBe Instruction(LEFT, 1)
        }
      }
    }

    Given("an instruction String with distance to advance greater than 9") {
      When("I parse that String into an Instruction") {
        Then("the Instruction should expose the correct distance to advance") {
          val instruction = Instruction("R101")

          instruction.distanceToAdvance shouldBe 101
        }
      }
    }
  }

}
