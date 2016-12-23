import java.io.File

class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)
      val instructions = inputFile
          .readLines()
          .map { instructionString -> Instruction.parse(instructionString) }

      val part1PrintWriter = File("day23/part_1_executions.txt").printWriter()

      val keypad = Keypad(a = 7)
      keypad.processInstructions(instructions, printWriter = part1PrintWriter)
      part1PrintWriter.close()
      println("Part 1: ${keypad.valueInRegisterA}")

      /*
       * Check day23/part_1_executions.txt for a list of all instructions executed while solving part 1. Then run
       * day23/part_1_instruction_execution_counts.sh to see how many times the instruction at each index was executed.
       * Manual inspection of this output leads to the manually-optimized input used to solve part 2 in a reasonable
       * length of time.
       */
//      val keypad2 = Keypad(a = 12)
//      keypad2.processInstructions(instructions)
//      println("Part 2: ${keypad2.valueInRegisterA}")
    }
  }

}
