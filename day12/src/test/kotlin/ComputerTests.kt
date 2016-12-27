import io.kotlintest.specs.BehaviorSpec

class ComputerTests : BehaviorSpec() {

  init {
    Given("a Computer whose registers all have initial value 0") {
      val computer = Computer(a = 0, b = 0, c = 0, d = 0)

      When("I process the sample instructions") {
        computer.processInstructions(listOf(
            WriteInstruction('a', 41),
            IncrementInstruction('a'),
            IncrementInstruction('a'),
            DecrementInstruction('a'),
            RegisterBasedJumpInstruction('a', 2),
            DecrementInstruction('a')
        ))

        Then("register 'a' should contain the value 42") {
          computer.valueInRegisterA shouldBe 42
        }
      }
    }
  }

}
