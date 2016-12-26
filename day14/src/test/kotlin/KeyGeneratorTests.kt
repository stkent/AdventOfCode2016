import io.kotlintest.specs.BehaviorSpec

class KeyGeneratorTests : BehaviorSpec() {

  init {
    Given("a key generator with the salt \"abc\"") {
      val keyGenerator = KeyGenerator(salt = "abc")

      When("I generate the 64th smallest key without applying password stretching") {
        val key = keyGenerator.generateKeys(64, applyKeyStretching = false).last()

        Then("the result should be 22728") {
          key shouldBe 22728
        }
      }

      When("I apply password stretching and generate the 64th smallest key, ") {
        val key = keyGenerator.generateKeys(64, applyKeyStretching = true).last()

        Then("the result should be 22551") {
          key shouldBe 22551
        }
      }
    }
  }

}
