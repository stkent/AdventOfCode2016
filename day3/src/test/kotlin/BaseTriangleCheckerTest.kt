import io.kotlintest.specs.BehaviorSpec

class BaseTriangleCheckerTest : BehaviorSpec() {
  
  init {
    Given("a BaseTriangleChecker") {
      When("I check all permutations of a list of valid triangle side lengths") {
        Then("all lists are assessed to represent a valid triangle") {
          val baseTriangleChecker = TestBaseTriangleChecker()

          baseTriangleChecker.isValidTriangle(listOf(3, 4, 5)) shouldBe true
          baseTriangleChecker.isValidTriangle(listOf(3, 5, 4)) shouldBe true
          baseTriangleChecker.isValidTriangle(listOf(4, 3, 5)) shouldBe true
          baseTriangleChecker.isValidTriangle(listOf(4, 5, 3)) shouldBe true
          baseTriangleChecker.isValidTriangle(listOf(5, 3, 4)) shouldBe true
          baseTriangleChecker.isValidTriangle(listOf(5, 4, 3)) shouldBe true
        }
      }

      When("I check all permutations of a list of invalid triangle side lengths") {
        Then("all lists are assessed to represent an invalid triangle") {
          val baseTriangleChecker = TestBaseTriangleChecker()

          baseTriangleChecker.isValidTriangle(listOf(3, 4, 10)) shouldBe false
          baseTriangleChecker.isValidTriangle(listOf(3, 10, 4)) shouldBe false
          baseTriangleChecker.isValidTriangle(listOf(4, 3, 10)) shouldBe false
          baseTriangleChecker.isValidTriangle(listOf(4, 10, 3)) shouldBe false
          baseTriangleChecker.isValidTriangle(listOf(10, 3, 4)) shouldBe false
          baseTriangleChecker.isValidTriangle(listOf(10, 4, 3)) shouldBe false
        }
      }
    }
  }
  
}
