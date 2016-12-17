import io.kotlintest.specs.BehaviorSpec

class RouterTests : BehaviorSpec() {

  init {
    Given("a router with salt \"ihgpwlah\"") {
      val router = Router(salt = "ihgpwlah")

      When("I compute the shortest path to the vault") {
        Then("The answer is \"DDRRRD\"") {
          router.shortestRouteToVault() shouldBe "DDRRRD"
        }
      }

      When("I compute the length of the longest path to the vault") {
        Then("The answer is 370") {
          router.lengthOfLongestRouteToVault() shouldBe 370
        }
      }
    }

    Given("a router with salt \"kglvqrro\"") {
      val router = Router(salt = "kglvqrro")

      When("I compute the shortest path to the vault") {
        Then("The answer is \"DDUDRLRRUDRD\"") {
          router.shortestRouteToVault() shouldBe "DDUDRLRRUDRD"
        }
      }

      When("I compute the length of the longest path to the vault") {
        Then("The answer is 492") {
          router.lengthOfLongestRouteToVault() shouldBe 492
        }
      }
    }

    Given("a router with salt \"ulqzkmiv\"") {
      val router = Router(salt = "ulqzkmiv")

      When("I compute the shortest path to the vault") {
        Then("The answer is \"DRURDRUDDLLDLUURRDULRLDUUDDDRR\"") {
          router.shortestRouteToVault() shouldBe "DRURDRUDDLLDLUURRDULRLDUUDDDRR"
        }
      }

      When("I compute the length of the longest path to the vault") {
        Then("The answer is 830") {
          router.lengthOfLongestRouteToVault() shouldBe 830
        }
      }
    }
  }

}
