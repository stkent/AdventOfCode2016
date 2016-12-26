import io.kotlintest.specs.BehaviorSpec

class DiskDataGeneratorTests : BehaviorSpec() {

  init {
    Given("a DiskDataGenerator for a disk of size 20") {
      val diskDataGenerator = DiskDataGenerator(size = 20)

      When("I generate data using the initial bits \"10000\"") {
        val generatedData = diskDataGenerator.generateData(initialBits = "10000")

        Then("the generated data has bits \"10000011110010000111\"") {
          generatedData.bits shouldBe "10000011110010000111"
        }

        Then("the generated data has checksum \"01100\"") {
          generatedData.checksum shouldBe "01100"
        }
      }
    }
  }

}
