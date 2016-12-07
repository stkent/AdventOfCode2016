import java.io.File

class Main {

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)

      val receivedMessages = inputFile.readLines()

      println(MessageParser(receivedMessages).parseUsingHighestFrequencyCharacters())
      println(MessageParser(receivedMessages).parseUsingLowestFrequencyCharacters())
    }
  }

}