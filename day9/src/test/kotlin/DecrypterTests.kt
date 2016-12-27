import io.kotlintest.specs.BehaviorSpec

class DecrypterTests : BehaviorSpec() {

  init {
    Given("a decrypter for an encrypted string that contains no markers") {
      val decrypter = Decrypter("ADVENT")

      When("I calculate the length of the decrypted string using algorithm 1") {
        val decryptedStringLength = decrypter.calculateLengthUsingAlgorithm1()

        Then("I should get the correct length") {
           decryptedStringLength shouldBe 6
        }
      }
    }

    Given("a decrypter for an encrypted string that contains a single marker that repeats a single character") {
      val decrypter = Decrypter("A(1x5)BC")
      When("I calculate the length of the decrypted string using algorithm 1") {
        val decryptedStringLength = decrypter.calculateLengthUsingAlgorithm1()

        Then("I should get the correct length") {
          decryptedStringLength shouldBe 7
        }
      }
    }

    Given("a decrypter for an encrypted string that contains a single marker that repeats multiple characters") {
      val decrypter = Decrypter("(3x3)XYZ")

      When("I calculate the length of the decrypted string using algorithm 1") {
        val decryptedStringLength = decrypter.calculateLengthUsingAlgorithm1()

        Then("I should get the correct length") {
          decryptedStringLength shouldBe 9
        }
      }
    }

    Given("a decrypter for an encrypted string that contains two non-overlapping markers") {
      val decrypter = Decrypter("A(2x2)BCD(2x2)EFG")

      When("I calculate the length of the decrypted string using algorithm 1") {
        val decryptedStringLength = decrypter.calculateLengthUsingAlgorithm1()

        Then("I should get the correct length") {
          decryptedStringLength shouldBe 11
        }
      }
    }

    Given("decrypters for encrypted strings that contains two consecutive markers") {
      val decrypter1 = Decrypter("(6x1)(1x3)A")
      val decrypter2 = Decrypter("X(8x2)(3x3)ABCY")

      When("I calculate the length of the decrypted string using algorithm 1") {
        val decryptedStringLength1 = decrypter1.calculateLengthUsingAlgorithm1()
        val decryptedStringLength2 = decrypter2.calculateLengthUsingAlgorithm1()

        Then("I should get the correct length (only the first marker should be expanded)") {
          decryptedStringLength1 shouldBe 6
          decryptedStringLength2 shouldBe 18
        }
      }
    }

    Given("a decrypter for an encrypted string that contains a single marker") {
      val decrypter = Decrypter("(3x3)XYZ")

      When("I calculate the length of the decrypted string using both algorithms 1 and 2") {
        val decryptedStringLength1 = decrypter.calculateLengthUsingAlgorithm1().toLong()
        val decryptedStringLength2 = decrypter.calculateLengthUsingAlgorithm2()

        Then("the two lengths should match (there are no markers to expand recursively)") {
          decryptedStringLength1 shouldBe decryptedStringLength2
        }
      }
    }

    Given("a decrypter for an encrypted string that contains two consecutive markers") {
      val decrypter = Decrypter("X(8x2)(3x3)ABCY")

      When("I calculate the length of the decrypted string using algorithm 2") {
        val decryptedStringLength = decrypter.calculateLengthUsingAlgorithm2()

        Then("I should get the correct length (both markers should be expanded)") {
          decryptedStringLength shouldBe 20L
        }
      }
    }

    Given("a decrypter for an encrypted string that contains more than two consecutive markers") {
      val decrypter = Decrypter("(27x12)(20x12)(13x14)(7x10)(1x12)A")

      When("I calculate the length of the decrypted string using algorithm 2") {
        val decryptedStringLength = decrypter.calculateLengthUsingAlgorithm2()

        Then("I should get the correct length (all markers should be expanded)") {
          decryptedStringLength shouldBe 241920L
        }
      }
    }

    Given("a decrypter for an encrypted string that contains a complicated collection of consecutive and non-consecutive markers") {
      val decrypter = Decrypter("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN")

      When("I calculate the length of the decrypted string using algorithm 2") {
        val decryptedStringLength = decrypter.calculateLengthUsingAlgorithm2()

        Then("I should get the correct length (marker expansion should occur in the correct order)") {
          decryptedStringLength shouldBe 445L
        }
      }
    }
  }

}
