import io.kotlintest.specs.BehaviorSpec

class ComputerTests : BehaviorSpec() {

  init {
    Given("a Computer whose registers all have initial value 0") {
      When("I process the sample instructions") {
        Then("register a should contain the value 42") {
          val computer = Computer()

          computer.processInstructions(listOf(
              "cpy 41 a",
              "inc a",
              "inc a",
              "dec a",
              "jnz a 2",
              "dec a"
          ))

          computer.valueInRegisterA shouldBe 42
        }
      }
    }
  }

}