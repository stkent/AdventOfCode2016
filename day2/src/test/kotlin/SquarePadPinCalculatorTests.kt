import io.kotlintest.specs.BehaviorSpec

class SquarePadPinCalculatorTests : BehaviorSpec() {

  init {
    Given("a PIN calculator for a square shaped pad") {
      When("I enter the instructions:" +
          "ULL" +
          "RRDDD" +
          "LURDL" +
          "UUUUD") {

        Then("the calculated PIN should be 1985") {
          SquarePadPinCalculator().calculatePinFromInstructions(listOf(
              "ULL",
              "RRDDD",
              "LURDL",
              "UUUUD"
          )) shouldBe "1985"
        }
      }
    }
  }

}
