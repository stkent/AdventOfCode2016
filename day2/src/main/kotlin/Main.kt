import java.io.File

class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)
      val instructions = inputFile.readLines()

      println("Part 1: ${SquarePadPinCalculator().calculatePinFromInstructions(instructions)}")
      println("Part 2: ${DiamondPadPinCalculator().calculatePinFromInstructions(instructions)}")
    }
  }

}
