import io.kotlintest.specs.BehaviorSpec

class BaseTriangleCheckerTests : BehaviorSpec() {

  init {
    Given("a BaseTriangleChecker") {
      val baseTriangleChecker = TestBaseTriangleChecker()

      When("I check all permutations of a list of valid triangle side lengths") {
        val allPermutationsRepresentValidTriangle = listOf(
            listOf(3, 4, 5),
            listOf(3, 5, 4),
            listOf(4, 3, 5),
            listOf(4, 5, 3),
            listOf(5, 3, 4),
            listOf(5, 4, 3))
                .all { baseTriangleChecker.isValidTriangle(it) }

        Then("it is confirmed that all permutations represent a valid triangle") {
          allPermutationsRepresentValidTriangle shouldBe true
        }
      }

      When("I check all permutations of a list of invalid triangle side lengths") {
        val allPermutationsRepresentInvalidTriangle = listOf(
            listOf( 3,  4, 10),
            listOf( 3, 10,  4),
            listOf( 4,  3, 10),
            listOf( 4, 10,  3),
            listOf(10,  3,  4),
            listOf(10,  4,  3))
                .all { !baseTriangleChecker.isValidTriangle(it) }

        Then("it is confirmed that no permutation represents a valid triangle") {
          allPermutationsRepresentInvalidTriangle shouldBe true
        }
      }
    }
  }

}
