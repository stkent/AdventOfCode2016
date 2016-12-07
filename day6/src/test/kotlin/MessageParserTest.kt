import io.kotlintest.specs.BehaviorSpec

class MessageParserTest : BehaviorSpec() {

  init {
    Given("") {
      When("") {
        Then("") {
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

          MessageParser(receivedMessages).parseUsingHighestFrequencyCharacters() shouldBe "easter"
          MessageParser(receivedMessages).parseUsingLowestFrequencyCharacters()  shouldBe "advent"
        }
      }
    }
  }

}