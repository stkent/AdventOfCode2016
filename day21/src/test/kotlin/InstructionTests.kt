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

    Given("") {
      When("") {
        Then("") {
          RotateByCharPositionInstruction('b').apply("abdec") shouldBe "ecabd"
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          RotateByCharPositionInstruction('d').apply("ecabd") shouldBe "decab"
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          ReverseInstruction(0, 4).apply("edcba") shouldBe "abcde"
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          MoveInstruction(1, 4).apply("bcdea") shouldBe "bdeac"
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          MoveInstruction(3, 0).apply("bdeac") shouldBe "abdec"
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          SwapPositionsInstruction(4, 0).apply("abcde") shouldBe "ebcda"
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          SwapLettersInstruction('d', 'b').apply("ebcda") shouldBe "edcba"
        }
      }
    }
  }

}
