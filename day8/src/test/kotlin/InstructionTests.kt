import io.kotlintest.matchers.be
import io.kotlintest.specs.BehaviorSpec

class InstructionTests : BehaviorSpec() {

  init {
    Given("a rect instruction String") {
      When("I parse that String into an Instruction") {
        Then("I get an Instruction of the correct type with the correct rect width and rect height data") {
          val instruction = Instruction.parse("rect 3x2")

          instruction should be a RectInstruction::class
          (instruction as RectInstruction)

          instruction.rectWidth shouldBe 3
          instruction.rectHeight shouldBe 2
        }
      }
    }

    Given("a rotate row instruction String") {
      When("I parse that String into an Instruction") {
        Then("I get an Instruction of the correct type with the correct row number and rotation amount") {
          val instruction = Instruction.parse("rotate row y=1 by 2")

          instruction should be a RotateRowInstruction::class
          (instruction as RotateRowInstruction)

          instruction.rowNumber shouldBe 1
          instruction.rotationAmount shouldBe 2
        }
      }
    }

    Given("a rotate column instruction String") {
      When("I parse that String into an Instruction") {
        Then("I get an Instruction of the correct type with the correct column number and rotation amount") {
          val instruction = Instruction.parse("rotate column x=1 by 2")

          instruction should be a RotateColumnInstruction::class
          (instruction as RotateColumnInstruction)

          instruction.columnNumber shouldBe 1
          instruction.rotationAmount shouldBe 2
        }
      }
    }
  }

}