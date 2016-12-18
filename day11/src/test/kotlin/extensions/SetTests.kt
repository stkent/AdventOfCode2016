package extensions

import io.kotlintest.specs.BehaviorSpec

class SetTests : BehaviorSpec() {

  init {
    Given("a set containing the integers 1, 2, 3, and 4") {
      val set = setOf(1, 2, 3, 4)

      When("I compute the power set of that set") {
        val powerSet = set.powerSet()

        Then("the result should include all subsets") {
          powerSet shouldBe setOf(
              emptySet(),
              setOf(1),
              setOf(2),
              setOf(3),
              setOf(4),
              setOf(1, 2),
              setOf(1, 3),
              setOf(1, 4),
              setOf(2, 3),
              setOf(2, 4),
              setOf(3, 4),
              setOf(1, 2, 3),
              setOf(1, 2, 4),
              setOf(1, 3, 4),
              setOf(2, 3, 4),
              setOf(1, 2, 3, 4)
          )
        }
      }
    }
  }

}
