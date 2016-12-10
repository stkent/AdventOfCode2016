import io.kotlintest.specs.BehaviorSpec

class DecrypterTests : BehaviorSpec() {

  init {
    Given("an encrypted string that contains no markers") {
      When("I calculate the length of the decrypted string using algorithm 1") {
        Then("I should get the correct length") {
          Decrypter("ADVENT").calculateLengthUsingAlgorithm1() shouldBe 6
        }
      }
    }

    Given("an encrypted string that contains a single marker that repeats a single character") {
      When("I calculate the length of the decrypted string using algorithm 1") {
        Then("I should get the correct length") {
          Decrypter("A(1x5)BC").calculateLengthUsingAlgorithm1() shouldBe 7
        }
      }
    }

    Given("an encrypted string that contains a single marker that repeats multiple characters") {
      When("I calculate the length of the decrypted string using algorithm 1") {
        Then("I should get the correct length") {
          Decrypter("(3x3)XYZ").calculateLengthUsingAlgorithm1() shouldBe 9
        }
      }
    }

    Given("an encrypted string that contains two non-overlapping markers") {
      When("I calculate the length of the decrypted string using algorithm 1") {
        Then("I should get the correct length") {
          Decrypter("A(2x2)BCD(2x2)EFG").calculateLengthUsingAlgorithm1() shouldBe 11
        }
      }
    }

    Given("an encrypted string that contains two consecutive markers") {
      When("I calculate the length of the decrypted string using algorithm 1") {
        Then("I should get the correct length (only the first marker should be expanded)") {
          Decrypter("(6x1)(1x3)A").calculateLengthUsingAlgorithm1() shouldBe 6
          Decrypter("X(8x2)(3x3)ABCY").calculateLengthUsingAlgorithm1() shouldBe 18
        }
      }
    }

    Given("an encrypted string that contains a single marker") {
      When("I calculate the length of the decrypted string using algorithm 2") {
        Then("I should get the same length as when using algorithm 1 (there are no markers to expand recursively)") {
          Decrypter("(3x3)XYZ").calculateLengthUsingAlgorithm2() shouldBe
              Decrypter("(3x3)XYZ").calculateLengthUsingAlgorithm1().toLong()
        }
      }
    }

    Given("an encrypted string that contains two consecutive markers") {
      When("I calculate the length of the decrypted string using algorithm 2") {
        Then("I should get the correct length (both markers should be expanded)") {
          Decrypter("X(8x2)(3x3)ABCY").calculateLengthUsingAlgorithm2() shouldBe 20L
        }
      }
    }

    Given("an encrypted string that contains more than two consecutive markers") {
      When("I calculate the length of the decrypted string using algorithm 2") {
        Then("I should get the correct length (all markers should be expanded)") {
          Decrypter("(27x12)(20x12)(13x14)(7x10)(1x12)A").calculateLengthUsingAlgorithm2() shouldBe 241920L
        }
      }
    }

    Given("an encrypted string that contains a complicated collection of consecutive and non-consecutive markers") {
      When("I calculate the length of the decrypted string using algorithm 2") {
        Then("I should get the correct length (marker expansion should occur in the correct order)") {
          Decrypter("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN")
              .calculateLengthUsingAlgorithm2() shouldBe 445L
        }
      }
    }
  }

}
