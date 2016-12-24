import io.kotlintest.specs.BehaviorSpec

class RouterTests : BehaviorSpec() {

  init {
    Given("the sample duct map") {
      val ductMap = DuctMap(rows = listOf(
          "###########",
          "#0.1.....2#",
          "#.#######.#",
          "#4.......3#",
          "###########"
      ))

      When("I compute the shortest route starting at 0 that passes through all other POIs and does not return to 0") {
        val lengthOfShortestRoutePassingThroughAllPOIs =
            Router(ductMap).computeLengthOfShortestRoutePassingThroughAllPOIs(mustReturnToInitialPOI = false)

        Then("the shortest route should be 14 steps long") {
          lengthOfShortestRoutePassingThroughAllPOIs shouldBe 14
        }
      }
    }
  }

}
