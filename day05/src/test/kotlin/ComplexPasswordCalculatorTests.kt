import io.kotlintest.specs.BehaviorSpec

class ComplexPasswordCalculatorTests : BehaviorSpec() {

  init {
    Given("a complex password calculator with seed \"abc\"") {
      val calculator = ComplexPasswordCalculator("abc")

      When("I calculate the generated password of length 8") {
        val generatedPassword = calculator.calculatePasswordOfLength(8)

        Then("the result should be \"05ace8e3\"") {
          generatedPassword shouldBe "05ace8e3"
        }
      }
    }
  }

}
