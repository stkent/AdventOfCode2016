import io.kotlintest.specs.BehaviorSpec

class KeypadTests : BehaviorSpec() {

  init {
    Given("a Keypad whose registers all have initial value 0") {
      val keypad = Keypad()

      When("I process the sample instructions") {
        keypad.processInstructions(listOf(
            CopyInstruction("2", "a"),
            ToggleInstruction('a'),
            ToggleInstruction('a'),
            ToggleInstruction('a'),
            CopyInstruction("1", "a"),
            DecrementInstruction('a'),
            DecrementInstruction('a')
        ))

        val valueInRegisterA = keypad.valueInRegisterA

        Then("register 'a' should contain the value 3") {
          valueInRegisterA shouldBe 3
        }
      }
    }
  }

}
