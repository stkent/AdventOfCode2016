import java.io.File

class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)

      val display = Display(rows = 6, columns = 50)

      inputFile.forEachLine { instructionString ->
        display.applyInstruction(Instruction.parse(instructionString))
      }

      println("Part 1: ${display.numberOfLitPixels}")
      println("Part 2: \n$display")
    }
  }

}
