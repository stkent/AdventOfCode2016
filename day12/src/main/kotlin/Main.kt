import java.io.File

class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)
      val instructions = inputFile
          .readLines()
          .map { instructionString -> Instruction.parse(instructionString) }

      val computer1 = Computer()
      computer1.processInstructions(instructions)
      println("Part 1: ${computer1.valueInRegisterA}")

      val computer2 = Computer(c = 1)
      computer2.processInstructions(instructions)
      println("Part 2: ${computer2.valueInRegisterA}")
    }
  }

}