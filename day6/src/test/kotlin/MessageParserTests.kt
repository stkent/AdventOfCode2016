import io.kotlintest.specs.BehaviorSpec

class MessageParserTests : BehaviorSpec() {

  init {
    Given("the relatively short sequence of received messages provided in the problem statement") {
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
        Then("the result is \"easter\"") {
          MessageParser(receivedMessages).parseUsingHighestFrequencyCharacters() shouldBe "easter"
        }
      }

      When("I calculate the real message by determining the lowest frequency character at each index") {
        Then("the result is \"advent\"") {
          MessageParser(receivedMessages).parseUsingLowestFrequencyCharacters()  shouldBe "advent"
        }
      }
    }
  }

}