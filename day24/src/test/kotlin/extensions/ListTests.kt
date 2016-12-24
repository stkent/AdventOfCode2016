package extensions

import io.kotlintest.specs.BehaviorSpec

class ListTests : BehaviorSpec() {

  init {
    Given("the list [1, 2, 3]") {
      val list = listOf(1, 2, 3)

      When("I convert this list to a list of chained pairs") {
        val chain = list.toChainedPairs()

        Then("the result is the list [Pair(1, 2), Pair(2, 3)]") {
          chain shouldBe listOf(Pair(1, 2), Pair(2, 3))
        }
      }
    }
  }

}
