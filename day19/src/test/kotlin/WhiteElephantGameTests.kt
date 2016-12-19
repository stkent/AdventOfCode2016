import io.kotlintest.specs.BehaviorSpec

class WhiteElephantGameTests : BehaviorSpec() {

  init {
    Given("a game with 5 players") {
      val whiteElephantGame = WhiteElephantGame(5)

      When("I compute the winner using version 1 of the rules") {
        val winner = whiteElephantGame.computeWinnerUsingV1Rules

        Then("the winner should be elf number 3") {
          winner shouldBe 3
        }
      }

      When("I compute the winner using version 2 of the rules") {
        val winner = whiteElephantGame.computeWinnerUsingV2Rules

        Then("the winner should be elf number 2") {
          winner shouldBe 2
        }
      }
    }

    Given("a game with 4 players") {
      val whiteElephantGame = WhiteElephantGame(4)

      When("I compute the winner using version 1 of the rules") {
        val winner = whiteElephantGame.computeWinnerUsingV1Rules

        Then("the winner should be elf number 1") {
          winner shouldBe 1
        }
      }

      When("I compute the winner using version 2 of the rules") {
        val winner = whiteElephantGame.computeWinnerUsingV2Rules

        Then("the winner should be elf number 1") {
          winner shouldBe 1
        }
      }
    }
  }

}
