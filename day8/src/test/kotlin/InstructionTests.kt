import io.kotlintest.matchers.be
import io.kotlintest.specs.BehaviorSpec

class InstructionTests : BehaviorSpec() {

  init {
    Given("a rect instruction String") {
      val instructionString = "rect 3x2"

      When("I parse that String into an Instruction") {
        val instruction = Instruction.parse(instructionString)

        Then("I get an Instruction of the correct type with the correct rect width and rect height data") {
          instruction should be a RectInstruction::class
          (instruction as RectInstruction)

          instruction.rectWidth shouldBe 3
          instruction.rectHeight shouldBe 2
        }
      }
    }

    Given("a rotate row instruction String") {
      val instructionString = "rotate row y=1 by 2"

      When("I parse that String into an Instruction") {
        val instruction = Instruction.parse(instructionString)

        Then("I get an Instruction of the correct type with the correct row number and rotation amount") {
          instruction should be a RotateRowInstruction::class
          (instruction as RotateRowInstruction)

          instruction.rowNumber shouldBe 1
          instruction.rotationAmount shouldBe 2
        }
      }
    }

    Given("a rotate column instruction String") {
      val instructionString = "rotate column x=1 by 2"

      When("I parse that String into an Instruction") {
        val instruction = Instruction.parse(instructionString)

        Then("I get an Instruction of the correct type with the correct column number and rotation amount") {
          instruction should be a RotateColumnInstruction::class
          (instruction as RotateColumnInstruction)

          instruction.columnNumber shouldBe 1
          instruction.rotationAmount shouldBe 2
        }
      }
    }
  }

}
