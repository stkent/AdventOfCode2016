import io.kotlintest.specs.BehaviorSpec

class RouterTests : BehaviorSpec() {

  init {
    Given("a Router seeded with the number 10") {
      val seed = 10

      val router = Router(
          spacePredicate = { l ->
            val sum = l.x * l.x + 3 * l.x + 2 * l.x * l.y + l.y + l.y * l.y + seed

            Integer
                .toBinaryString(sum)
                .groupBy { it }['1'].orEmpty()
                .size % 2 == 0
          }
      )

      When("I calculate the shortest route between (1, 1) and (7, 4)") {
        val lengthOfShortestRoute = router.lengthOfShortestRouteBetween(Location(1, 1), Location(7, 4))

        Then("the answer should be 11") {
          lengthOfShortestRoute shouldBe 11
        }
      }
    }
  }

}
