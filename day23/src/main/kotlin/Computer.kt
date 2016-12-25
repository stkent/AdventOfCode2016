import java.io.PrintWriter

class Computer(a: Long = 0, b: Long = 0, c: Long = 0, d: Long = 0) {

  private val registers: MutableMap<Char, Long>

  init {
    registers = mutableMapOf(
        Pair('a', a),
        Pair('b', b),
        Pair('c', c),
        Pair('d', d)
    )
  }

  override fun toString(): String {
    return registers.toString()
  }

  val valueInRegisterA: Long
    get() = registers['a']!!

  fun processInstructions(initialInstructions: List<Instruction>, printWriter: PrintWriter? = null) {
    var currentInstructions = initialInstructions
    var currentInstructionIndex = 0

    while (currentInstructionIndex < currentInstructions.size) {
      val currentInstruction = currentInstructions[currentInstructionIndex]

      updateRegisterValues(currentInstruction)

      printWriter?.println(listOf(
          currentInstructionIndex.toString().padStart(3),
          currentInstruction.toString().padEnd(9),
          registers).joinToString(separator = " | "))

      currentInstructions = computeUpdatedInstructions(currentInstructions, currentInstructionIndex, currentInstruction)
      currentInstructionIndex = computeNextInstructionIndex(currentInstructionIndex, currentInstruction)
    }
  }

  private fun updateRegisterValues(instruction: Instruction) {
    when (instruction) {
      is IncrementInstruction -> {
        registers.put(instruction.targetRegisterName, registers[instruction.targetRegisterName]!! + 1)
      }

      is DecrementInstruction -> {
        registers.put(instruction.targetRegisterName, registers[instruction.targetRegisterName]!! - 1)
      }

      is CopyInstruction -> {
        if (!registers.keys.contains(instruction.secondArg.first())) {
          // The current copy instruction is not valid; no-op.
          return
        }

        val targetRegisterName = instruction.secondArg.first()

        try {
          val value = instruction.firstArg.toLong()
          registers.put(targetRegisterName, value)
        } catch (exception: NumberFormatException) {
          val sourceRegisterName = instruction.firstArg.first()
          registers.put(targetRegisterName, registers[sourceRegisterName]!!)
        }
      }
    }
  }

  private fun computeUpdatedInstructions(
      instructions: List<Instruction>,
      currentInstructionIndex: Int,
      currentInstruction: Instruction): List<Instruction> {

    when (currentInstruction) {
      is ToggleInstruction -> {
        val indexToToggle = currentInstructionIndex + registers[currentInstruction.targetRegisterName]!!.toInt()

        if (indexToToggle > instructions.lastIndex) {
          return instructions
        }

        val result = mutableListOf<Instruction>()

        (0..instructions.lastIndex).mapTo(result) { index ->
          if (index == indexToToggle) instructions[indexToToggle].toggle() else instructions[index]
        }

        return result
      }

      else -> return instructions
    }
  }

  private fun computeNextInstructionIndex(currentInstructionIndex: Int, instruction: Instruction): Int {
    return currentInstructionIndex + (
        when (instruction) {
          is JumpInstruction -> {
            val controlValue = try {
              instruction.firstArg.toInt()
            } catch (exception: NumberFormatException) {
              registers[instruction.firstArg.first()]!!.toInt()
            }

            val jumpAmount = try {
              instruction.secondArg.toInt()
            } catch (exception: NumberFormatException) {
              registers[instruction.secondArg.first()]!!.toInt()
            }

            if (controlValue != 0) jumpAmount else 1
          }

          else -> 1
        }
    )
  }

}
