import io.kotlintest.specs.BehaviorSpec

class FactoryTests : BehaviorSpec() {

  init {
    Given("a factory") {
      val factory = Factory()

      When("I process a set of instructions") {
        factory.processInstructions(listOf(
            "value 5 goes to bot 2",
            "bot 2 gives low to bot 1 and high to bot 0",
            "value 3 goes to bot 1",
            "bot 1 gives low to output 1 and high to bot 0",
            "bot 0 gives low to output 2 and high to output 0",
            "value 2 goes to bot 2"
        ))

        Then("all chips should end up in the correct output bins") {
          factory.outputs shouldBe mapOf(
              Pair(0, 5),
              Pair(1, 2),
              Pair(2, 3)
          )
        }
      }
    }
  }

}
