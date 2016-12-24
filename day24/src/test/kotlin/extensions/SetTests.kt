package extensions

import io.kotlintest.specs.BehaviorSpec

class SetTests : BehaviorSpec() {

  init {
    Given("a set containing the integers 1, 2, and 3") {
      val set = setOf(1, 2, 3)

      When("I compute all permutations of the elements in that set") {
        val permutations = set.permutations()

        Then("all expected permutations should be returned") {
          permutations shouldBe setOf(
              listOf(1, 2, 3),
              listOf(1, 3, 2),
              listOf(2, 1, 3),
              listOf(2, 3, 1),
              listOf(3, 1, 2),
              listOf(3, 2, 1)
          )
        }
      }
    }
  }

}
