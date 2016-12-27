import io.kotlintest.specs.BehaviorSpec

class SquarePadPinCalculatorTests : BehaviorSpec() {

  init {
    Given("a PIN calculator for a square shaped pad") {
      val calculator = SquarePadPinCalculator()

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

        Then("the calculated PIN should be 1985") {
          calculatedPin shouldBe "1985"
        }
      }
    }
  }

}
