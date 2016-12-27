import io.kotlintest.specs.BehaviorSpec

class DisplayTests : BehaviorSpec() {

  init {
    Given("a 3x7 display") {
      val display = Display(3, 7)

      When("I inspect it in its initial state") {
        val initialDisplayState = display.toString()

        Then("all pixels should be off") {
          initialDisplayState shouldBe
              ".......\n" +
              ".......\n" +
              "......."
        }
      }
    }

    Given("a 3x7 display") {
      val display = Display(3, 7)

      When("I apply several instructions and inspect the resulting state") {
        display.applyInstruction(RectInstruction(3, 2))
        display.applyInstruction(RotateColumnInstruction(1, 1))
        display.applyInstruction(RotateRowInstruction(0, 4))
        display.applyInstruction(RotateColumnInstruction(1, 1))

        val finalDisplayState = display.toString()

        Then("the correct pixels should be on") {
          finalDisplayState shouldBe
              ".#..#.#\n" +
              "#.#....\n" +
              ".#....."
        }
      }
    }
  }

}
