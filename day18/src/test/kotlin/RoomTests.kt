import io.kotlintest.specs.BehaviorSpec

class RoomTests : BehaviorSpec() {

  init {
    Given("a room with first row \"..^^.\"") {
      val room = Room(firstRowTileTypes = "..^^.")

      When("I compute the number of safe tiles in the first 3 rows") {
        val numberOfTrapsInFirst3Rows = room.computeNumberOfSafeTiles(numberOfRows = 3)

        Then("the answer should be 6") {
          numberOfTrapsInFirst3Rows shouldBe 6
        }
      }
    }

    Given("a room with first row \".^^.^.^^^^\"") {
      val room = Room(firstRowTileTypes = ".^^.^.^^^^")

      When("I compute the number of safe tiles in the first 10 rows") {
        val numberOfTrapsInFirst10Rows = room.computeNumberOfSafeTiles(numberOfRows = 10)

        Then("the answer should be 38") {
          numberOfTrapsInFirst10Rows shouldBe 38
        }
      }
    }
  }

}
