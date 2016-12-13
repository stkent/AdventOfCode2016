interface Instruction {

  companion object {
    fun parse(instructionString: String): Instruction {
      return when {
        instructionString.startsWith("inc") -> IncrementInstruction(instructionString.last())

        instructionString.startsWith("dec") -> DecrementInstruction(instructionString.last())

        instructionString.startsWith("jnz") -> {
          val components = instructionString.split(Regex("\\s"))

          try {
            ValueBasedJumpInstruction(components[1].toInt(), components[2].toInt())
          } catch (ignored: NumberFormatException) {
            RegisterBasedJumpInstruction(components[1].toCharArray().first(), components[2].toInt())
          }
        }

        instructionString.startsWith("cpy") -> {
          val components = instructionString.split(Regex("\\s"))

          try {
            WriteInstruction(components[2].toCharArray().first(), components[1].toInt())
          } catch (ignored: NumberFormatException) {
            CopyInstruction(components[1].toCharArray().first(), components[2].toCharArray().first())
          }
        }

        else -> throw IllegalArgumentException("This instruction is not recognized!")
      }
    }
  }

}

data class WriteInstruction(val targetRegisterName: Char, val value: Int) : Instruction

data class CopyInstruction(val sourceRegisterName: Char, val targetRegisterName: Char) : Instruction

data class IncrementInstruction(val targetRegisterName: Char) : Instruction

data class DecrementInstruction(val targetRegisterName: Char) : Instruction

data class RegisterBasedJumpInstruction(val sourceRegister: Char, val jump: Int) : Instruction

data class ValueBasedJumpInstruction(val value: Int, val jump: Int) : Instruction
