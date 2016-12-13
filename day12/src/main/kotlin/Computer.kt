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

  fun processInstructions(instructionStrings: List<String>) {
    val instructions = instructionStrings.map { instructionString -> Instruction.parse(instructionString) }

    var instructionIndex = 0

    while (instructionIndex < instructions.size) {
      instructionIndex += applyInstruction(instructions[instructionIndex])
    }
  }

  /**
   * @return the number of instructions to move; typically 1, unless we're parsing a jump instruction.
   */
  private fun applyInstruction(instruction: Instruction): Int {
    when (instruction) {
      is IncrementInstruction -> {
        registers.put(instruction.targetRegisterName, registers[instruction.targetRegisterName]!! + 1)
        return 1
      }

      is DecrementInstruction -> {
        registers.put(instruction.targetRegisterName, registers[instruction.targetRegisterName]!! - 1)
        return 1
      }

      is RegisterBasedJumpInstruction -> {
        val sourceRegisterValue = registers[instruction.sourceRegister]!!

        return if (sourceRegisterValue != 0) {
          instruction.jump
        } else {
          1
        }
      }

      is ValueBasedJumpInstruction -> {
        return if (instruction.value != 0) {
          instruction.jump
        } else {
          1
        }
      }

      is CopyInstruction -> {
        registers.put(instruction.targetRegisterName, registers[instruction.sourceRegisterName]!!)
        return 1
      }

      is WriteInstruction -> {
        registers.put(instruction.targetRegisterName, instruction.value)
        return 1
      }

      else -> throw IllegalArgumentException("This instruction is not recognized!")
    }
  }

}
