abstract class BasePinCalculator {

  protected abstract fun applyInstruction(char: Char)

  protected abstract val key: String

  fun calculatePinFromInstructions(instructions: List<String>): String {
    var result = ""

    instructions.forEach { instruction ->
      instruction.toCharArray().forEach {
        applyInstruction(it)
      }

      result = "$result$key"
    }

    return result
  }

}
