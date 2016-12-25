import java.io.File

class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)
      val instructions = inputFile
          .readLines()
          .map { instructionString -> Instruction.parse(instructionString) }

      val part1PrintWriter = File("day23/part_1_executions.txt").printWriter()

      val computer = Computer(a = 7)
      computer.processInstructions(instructions, printWriter = part1PrintWriter)
      part1PrintWriter.close()
      println("Part 1: ${computer.valueInRegisterA}")

      /*
       * Check day23/part_1_executions.txt for a list of all instructions executed while solving part 1. Then run
       * day23/part_1_instruction_execution_counts.sh to see how many times the instruction at each index was executed.
       * Manual inspection of this output and optimization of the input (replacing increment/decrement instructions that
       * are executed many times by a multiplicative instruction that is executed once) could be used to significantly
       * reduce the time needed to run part 2.
       */
      val computer2 = Computer(a = 12)
      computer2.processInstructions(instructions)
      println("Part 2: ${computer2.valueInRegisterA}")
    }
  }

}
