import io.kotlintest.specs.BehaviorSpec

class MessageParserTests : BehaviorSpec() {

  init {
    Given("the relatively short sequence of received messages provided in the puzzle statement") {
      val receivedMessages = listOf(
          "eedadn",
          "drvtee",
          "eandsr",
          "rasrtv",
          "nssdts",
          "raavrd",
          "atevrs",
          "tsrnev",
          "sdttsa",
          "ntnada",
          "svetve",
          "tesnvt",
          "vntsnd",
          "vrdear",
          "dvrsen",
          "enarar"
      )

      When("I calculate the real message by determining the highest frequency character at each index") {
        val realMessage = MessageParser(receivedMessages).parseUsingHighestFrequencyCharacters()

        Then("the result is \"easter\"") {
          realMessage shouldBe "easter"
        }
      }

      When("I calculate the real message by determining the lowest frequency character at each index") {
        val realMessage = MessageParser(receivedMessages).parseUsingLowestFrequencyCharacters()

        Then("the result is \"advent\"") {
          realMessage shouldBe "advent"
        }
      }
    }
  }

}
