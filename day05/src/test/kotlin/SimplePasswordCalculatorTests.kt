import io.kotlintest.specs.BehaviorSpec

class SimplePasswordCalculatorTests : BehaviorSpec() {

  init {
    Given("a simple password calculator with seed \"abc\"") {
      val calculator = SimplePasswordCalculator("abc")

      When("I calculate the generated password of length 8") {
        val generatedPassword = calculator.calculatePasswordOfLength(8)

        Then("the result should be \"18f47a30\"") {
          generatedPassword shouldBe "18f47a30"
        }
      }
    }
  }

}
