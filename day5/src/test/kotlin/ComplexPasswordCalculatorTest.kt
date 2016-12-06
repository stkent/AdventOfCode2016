import io.kotlintest.specs.BehaviorSpec

class ComplexPasswordCalculatorTest : BehaviorSpec() {
  
  init {
    Given("the password seed \"abc\"") {
      When("I calculate the generated password of length 8") {
        Then("the result should be \"05ace8e3\"") {
          ComplexPasswordCalculator("abc").calculatePasswordOfLength(8) shouldBe "05ace8e3"
        }
      }
    }
  }
  
}