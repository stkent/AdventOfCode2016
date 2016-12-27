import java.io.File

class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)

      val instructions = inputFile
          .readLines()
          .first()
          .split(", ")
          .map(::Instruction)

      println("Part 1: ${Navigator().computeDistanceToFinalLocation(instructions)}")
      println("Part 2: ${Navigator().computeDistanceToFirstLocationVisitedTwice(instructions)}")
    }
  }

}
