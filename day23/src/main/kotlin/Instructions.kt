interface Instruction {

  companion object {
    fun parse(instructionString: String): Instruction {
      return when {
        instructionString.startsWith("inc") -> IncrementInstruction(instructionString.last())

        instructionString.startsWith("dec") -> DecrementInstruction(instructionString.last())

        instructionString.startsWith("tgl") -> ToggleInstruction(instructionString.last())

        instructionString.startsWith("cpy") -> {
          val components = instructionString.split(Regex("\\s"))
          CopyInstruction(components[1], components[2])
        }

        instructionString.startsWith("jnz") -> {
          val components = instructionString.split(Regex("\\s"))
          JumpInstruction(components[1], components[2])
        }

        else -> throw IllegalArgumentException("This instruction is not recognized!")
      }
    }
  }

  fun toggle(): Instruction

}

// Single argument instructions

data class IncrementInstruction(val targetRegisterName: Char) : Instruction {

  override fun toggle(): Instruction {
    return DecrementInstruction(targetRegisterName)
  }

  override fun toString(): String {
    return "inc $targetRegisterName"
  }

}

data class DecrementInstruction(val targetRegisterName: Char) : Instruction {

  override fun toggle(): Instruction {
    return IncrementInstruction(targetRegisterName)
  }

  override fun toString(): String {
    return "dec $targetRegisterName"
  }

}

data class ToggleInstruction(val targetRegisterName: Char) : Instruction {

  override fun toggle(): Instruction {
    return IncrementInstruction(targetRegisterName)
  }

  override fun toString(): String {
    return "tgl $targetRegisterName"
  }

}

// Double argument instructions

data class CopyInstruction(val firstArg: String, val secondArg: String) : Instruction {

  override fun toggle(): Instruction {
    return JumpInstruction(firstArg, secondArg)
  }

  override fun toString(): String {
    return "cpy $firstArg $secondArg"
  }

}

data class JumpInstruction(val firstArg: String, val secondArg: String) : Instruction {

  override fun toggle(): Instruction {
    return CopyInstruction(firstArg, secondArg)
  }

  override fun toString(): String {
    return "jnz $firstArg $secondArg"
  }

}
