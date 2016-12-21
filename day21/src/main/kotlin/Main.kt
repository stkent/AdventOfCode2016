import extensions.applyInstruction
import java.io.File

class Main {

  companion object {

    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)

      var result = "abcdefgh"

      inputFile
          .readLines()
          .map { line -> Instruction.parse(line) }
          .filterNotNull()
          .forEach { instruction ->
            result = result.applyInstruction(instruction)
          }

      println("Part 1: $result")

      var result2 = "fbgdceah"

      inputFile
          .readLines()
          .reversed()
          .map { line -> Instruction.parse(line) }
          .filterNotNull()
          .map(Instruction::invert)
          .forEach { instruction ->
            result2 = result2.applyInstruction(instruction)
          }

      println("Part 2: $result2")
    }
  }

}
