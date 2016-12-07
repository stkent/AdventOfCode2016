import java.io.File

class Main {

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)

      val receivedMessages = inputFile.readLines()

      println("Part 1: ${MessageParser(receivedMessages).parseUsingHighestFrequencyCharacters()}")
      println("Part 2: ${MessageParser(receivedMessages).parseUsingLowestFrequencyCharacters()}")
    }
  }

}
