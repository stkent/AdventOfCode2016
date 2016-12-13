import java.io.File

class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)
      val instructionStrings = inputFile.readLines()

      val computer1 = Computer()
      computer1.processInstructions(instructionStrings)
      println("Part 1: ${computer1.valueInRegisterA}")

      val computer2 = Computer(c = 1)
      computer2.processInstructions(instructionStrings)
      println("Part 2: ${computer2.valueInRegisterA}")
    }
  }

}