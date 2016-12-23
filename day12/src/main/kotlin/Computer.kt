class Computer(a: Int = 0, b: Int = 0, c: Int = 0, d: Int = 0) {

  private val registers: MutableMap<Char, Int>

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

  val valueInRegisterA: Int
    get() = registers['a']!!

  fun processInstructions(instructions: List<Instruction>) {
    var instructionIndex = 0

    while (instructionIndex < instructions.size) {
      updateRegisterValues(instructions[instructionIndex])
      instructionIndex += computeNextInstructionIndexOffset(instructions[instructionIndex])
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
        registers.put(instruction.targetRegisterName, registers[instruction.sourceRegisterName]!!)
      }

      is WriteInstruction -> {
        registers.put(instruction.targetRegisterName, instruction.value)
      }
    }
  }

  private fun computeNextInstructionIndexOffset(instruction: Instruction): Int {
    when (instruction) {
      is RegisterBasedJumpInstruction -> {
        val sourceRegisterValue = registers[instruction.sourceRegister]!!

        return if (sourceRegisterValue != 0) instruction.jump else 1
      }

      is ValueBasedJumpInstruction -> {
        return if (instruction.value != 0) instruction.jump else 1
      }

      else -> return 1
    }
  }

}
