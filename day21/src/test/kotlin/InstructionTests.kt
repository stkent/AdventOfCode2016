import extensions.applyInstruction
import io.kotlintest.specs.BehaviorSpec

class InstructionTests : BehaviorSpec() {

  init {
    Given("an instruction to rotate by 1 step") {
      val instruction = RotateByAmountInstruction(1)

      When("I apply the instruction to the string \"abcd\"") {
        val rotatedString = "abcd".applyInstruction(instruction)

        Then("the result should be \"dabc\"") {
          rotatedString shouldBe "dabc"
        }
      }
    }

    Given("an instruction to rotate by a number of steps equal to the index of the character 'b'") {
      val instruction = RotateByCharPositionInstruction('b')

      When("I apply the instruction to the string \"abdec\"") {
        val updatedString = "abdec".applyInstruction(instruction)

        Then("the result should be \"ecabd\"") {
          updatedString shouldBe "ecabd"
        }
      }
    }

    Given("an instruction to rotate by a number of steps equal to the index of the character 'd'") {
      val instruction = RotateByCharPositionInstruction('d')

      When("I apply the instruction to the string \"ecabd\"") {
        val updatedString = "ecabd".applyInstruction(instruction)

        Then("the result should be \"decab\"") {
          updatedString shouldBe "decab"
        }
      }
    }

    Given("an instruction to reverse the substring between indices 0 and 4") {
      val instruction = ReverseInstruction(0, 4)

      When("I apply the instruction to the string \"edcba\"") {
        val updatedString = "edcba".applyInstruction(instruction)

        Then("the result should be \"abcde\"") {
          updatedString shouldBe "abcde"
        }
      }
    }

    Given("an instruction to move the character with index 1 to index 4") {
      val instruction = MoveInstruction(1, 4)

      When("I apply the instruction to the string \"bcdea\"") {
        val updatedString = "bcdea".applyInstruction(instruction)

        Then("the result should be \"bdeac\"") {
          updatedString shouldBe "bdeac"
        }
      }
    }

    Given("an instruction to move the character with index 3 to index 0") {
      val instruction = MoveInstruction(3, 0)

      When("I apply the instruction to the string \"bdeac\"") {
        val updatedString = "bdeac".applyInstruction(instruction)

        Then("the result should be \"abdec\"") {
          updatedString shouldBe "abdec"
        }
      }
    }

    Given("an instruction to swap the positions of the characters at indices 4 and 0") {
      val instruction = SwapPositionsInstruction(4, 0)

      When("I apply the instruction to the string \"abcde\"") {
        val updatedString = "abcde".applyInstruction(instruction)

        Then("the result should be \"ebcda\"") {
          updatedString shouldBe "ebcda"
        }
      }
    }

    Given("an instruction to swap the letters 'd' and 'b'") {
      val instruction = SwapLettersInstruction('d', 'b')

      When("I apply the instruction to the string \"ebcda\"") {
        val updatedString = "ebcda".applyInstruction(instruction)

        Then("the result should be \"edcba\"") {
          updatedString shouldBe "edcba"
        }
      }
    }
  }

}
