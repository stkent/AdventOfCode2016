import io.kotlintest.specs.BehaviorSpec

class SimplePasswordCalculatorTests : BehaviorSpec() {

  init {
    Given("the password seed \"abc\"") {
      When("I calculate the generated password of length 8") {
        Then("the result should be \"18f47a30\"") {
          SimplePasswordCalculator("abc").calculatePasswordOfLength(8) shouldBe "18f47a30"
        }
      }
    }
  }

}