import io.kotlintest.specs.BehaviorSpec

class ComputerTests : BehaviorSpec() {

  init {
    Given("a Computer whose registers all have initial value 0") {
      When("I process the sample instructions") {
        Then("register 'a' should contain the value 42") {
          val computer = Computer()

          computer.processInstructions(listOf(
              WriteInstruction('a', 41),
              IncrementInstruction('a'),
              IncrementInstruction('a'),
              DecrementInstruction('a'),
              RegisterBasedJumpInstruction('a', 2),
              DecrementInstruction('a')
          ))

          computer.valueInRegisterA shouldBe 42
        }
      }
    }
  }

}