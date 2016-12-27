import io.kotlintest.specs.BehaviorSpec

class DiamondPadPinCalculatorTests : BehaviorSpec() {

  init {
    Given("a PIN calculator for a diamond shaped pad") {
      val calculator = DiamondPadPinCalculator()

      When("I enter the instructions:" +
          "ULL" +
          "RRDDD" +
          "LURDL" +
          "UUUUD") {

        val calculatedPin = calculator.calculatePinFromInstructions(listOf(
            "ULL",
            "RRDDD",
            "LURDL",
            "UUUUD"
        ))

        Then("the calculated PIN should be 5DB3") {
          calculatedPin shouldBe "5DB3"
        }
      }
    }
  }

}
