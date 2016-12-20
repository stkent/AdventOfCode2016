import io.kotlintest.specs.BehaviorSpec

class AddressCheckerTests : BehaviorSpec() {

  init {
    Given("an address checker with addresses 0, 1, 2, 4, 5, 6, 7, and 8 blacklisted") {
      val addressChecker = AddressChecker(
          blacklistedAddresses = listOf(LongRange(5, 8), LongRange(0, 2), LongRange(4, 7)))

      When("I ask for the first valid address between 0 and 9 inclusive") {
        val firstValidAddress = addressChecker.findFirstValidAddress(candidateAddresses = LongRange(0, 9))

        Then("I am correctly told the answer is address 3") {
          firstValidAddress shouldBe 3.toLong()
        }
      }

      When("I ask how many of the addresses between 0 and 9 inclusive are valid") {
        val numberOfValidAddresses = addressChecker.computeNumberOfValidAddresses(candidateAddresses = LongRange(0, 9))

        Then("I am correctly told the answer is 2") {
          numberOfValidAddresses shouldBe 2.toLong()
        }
      }
    }
  }

}
