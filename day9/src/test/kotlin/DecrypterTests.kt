import io.kotlintest.specs.BehaviorSpec

class DecrypterTests : BehaviorSpec() {

  init {
    Given("") {
      When("") {
        Then("") {
          val decrypter = Decrypter("ADVENT")
          decrypter.decryptUsingFormat1() shouldBe "ADVENT"
          decrypter.lengthWhenDecryptedUsingFormat1() shouldBe 6
        }
      }

      When("") {
        Then("") {
          val decrypter = Decrypter("A(1x5)BC")
          decrypter.decryptUsingFormat1() shouldBe "ABBBBBC"
          decrypter.lengthWhenDecryptedUsingFormat1() shouldBe 7
        }
      }

      When("") {
        Then("") {
          val decrypter = Decrypter("(3x3)XYZ")
          decrypter.decryptUsingFormat1() shouldBe "XYZXYZXYZ"
          decrypter.lengthWhenDecryptedUsingFormat1() shouldBe 9
        }
      }

      When("") {
        Then("") {
          val decrypter = Decrypter("A(2x2)BCD(2x2)EFG")
          decrypter.decryptUsingFormat1() shouldBe "ABCBCDEFEFG"
          decrypter.lengthWhenDecryptedUsingFormat1() shouldBe 11
        }
      }

      When("") {
        Then("") {
          val decrypter = Decrypter("(6x1)(1x3)A")
          decrypter.decryptUsingFormat1() shouldBe "(1x3)A"
          decrypter.lengthWhenDecryptedUsingFormat1() shouldBe 6
        }
      }

      When("") {
        Then("") {
          val decrypter = Decrypter("X(8x2)(3x3)ABCY")
          decrypter.decryptUsingFormat1() shouldBe "XABCABCABCABCABCABCY"
          decrypter.lengthWhenDecryptedUsingFormat1() shouldBe 18
        }
      }

      When("") {
        Then("") {
          Decrypter("(3x3)XYZ").lengthWhenDecryptedUsingFormat2() shouldBe 9
        }
      }

      When("") {
        Then("") {
          Decrypter("X(8x2)(3x3)ABCY").lengthWhenDecryptedUsingFormat2() shouldBe 20
        }
      }

      When("") {
        Then("") {
          Decrypter("(27x12)(20x12)(13x14)(7x10)(1x12)A").lengthWhenDecryptedUsingFormat2() shouldBe 241920
        }
      }

      When("") {
        Then("") {
          Decrypter("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN")
              .lengthWhenDecryptedUsingFormat2() shouldBe 445
        }
      }
    }
  }

}