import io.kotlintest.specs.BehaviorSpec

class DisplayTests : BehaviorSpec() {

  init {
    Given("a 3x7 display") {
      When("I inspect it in its initial state") {
        Then("all pixels should be off") {
          Display(3, 7).toString() shouldBe
              ".......\n" +
              ".......\n" +
              "......."
        }
      }

      When("I apply several instructions") {
        Then("the correct pixels should be on") {
          val display = Display(3, 7)

          display.applyInstruction(RectInstruction(3, 2))
          display.applyInstruction(RotateColumnInstruction(1, 1))
          display.applyInstruction(RotateRowInstruction(0, 4))
          display.applyInstruction(RotateColumnInstruction(1, 1))

          display.toString() shouldBe
              ".#..#.#\n" +
              "#.#....\n" +
              ".#....."
        }
      }
    }
  }

}
