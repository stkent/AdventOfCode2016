import io.kotlintest.specs.BehaviorSpec

class DiamondPadPinCalculatorTests : BehaviorSpec() {

  init {
    Given("a PIN calculator for a diamond shaped pad") {
      When("I enter the instructions:" +
          "ULL" +
          "RRDDD" +
          "LURDL" +
          "UUUUD") {

        Then("the calculated PIN should be 5DB3") {
          DiamondPadPinCalculator().calculatePinFromInstructions(listOf(
              "ULL",
              "RRDDD",
              "LURDL",
              "UUUUD"
          )) shouldBe "5DB3"
        }
      }
    }
  }

}
