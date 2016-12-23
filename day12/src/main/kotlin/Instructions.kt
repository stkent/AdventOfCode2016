interface Instruction {

  companion object {
    fun parse(instructionString: String): Instruction {
      return when {
        instructionString.startsWith("inc") -> IncrementInstruction(instructionString.last())

        instructionString.startsWith("dec") -> DecrementInstruction(instructionString.last())

        instructionString.startsWith("cpy") -> {
          val components = instructionString.split(Regex("\\s"))

          try {
            WriteInstruction(components[2].first(), components[1].toInt())
          } catch (ignored: NumberFormatException) {
            CopyInstruction(components[1].first(), components[2].first())
          }
        }

        instructionString.startsWith("jnz") -> {
          val components = instructionString.split(Regex("\\s"))

          try {
            ValueBasedJumpInstruction(components[1].toInt(), components[2].toInt())
          } catch (ignored: NumberFormatException) {
            RegisterBasedJumpInstruction(components[1].first(), components[2].toInt())
          }
        }

        else -> throw IllegalArgumentException("This instruction is not recognized!")
      }
    }
  }

}

// Single argument instructions

data class IncrementInstruction(val targetRegisterName: Char) : Instruction

data class DecrementInstruction(val targetRegisterName: Char) : Instruction

// Double argument instructions

data class WriteInstruction(val targetRegisterName: Char, val value: Int) : Instruction

data class CopyInstruction(val sourceRegisterName: Char, val targetRegisterName: Char) : Instruction

data class RegisterBasedJumpInstruction(val sourceRegister: Char, val jump: Int) : Instruction

data class ValueBasedJumpInstruction(val value: Int, val jump: Int) : Instruction
