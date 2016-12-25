import io.kotlintest.specs.BehaviorSpec

class ComputerTests : BehaviorSpec() {

  init {
    Given("a Computer whose registers all have initial value 0") {
      val computer = Computer()

      When("I process the sample instructions") {
        computer.processInstructions(listOf(
            CopyInstruction("2", "a"),
            ToggleInstruction('a'),
            ToggleInstruction('a'),
            ToggleInstruction('a'),
            CopyInstruction("1", "a"),
            DecrementInstruction('a'),
            DecrementInstruction('a')
        ))

        val valueInRegisterA = computer.valueInRegisterA

        Then("register 'a' should contain the value 3") {
          valueInRegisterA shouldBe 3
        }
      }
    }
  }

}
