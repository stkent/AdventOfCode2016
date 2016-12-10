interface Instruction {

  companion object {

    private val VALUE_INSTRUCTION_REGEX = Regex("value (\\d+) goes to bot (\\d+)")

    private val DISTRIBUTION_INSTRUCTION_REGEX =
        Regex("bot (\\d+) gives low to (bot|output) (\\d+) and high to (bot|output) (\\d+)")

    fun parse(rawInstruction: String): Instruction {
      return when {
        VALUE_INSTRUCTION_REGEX.matches(rawInstruction) -> {
          val matchResult = VALUE_INSTRUCTION_REGEX.matchEntire(rawInstruction)!!.groupValues
          ValueInstruction(
              botNumber = matchResult[2].toInt(),
              chip = matchResult[1].toInt())
        }

        DISTRIBUTION_INSTRUCTION_REGEX.matches(rawInstruction) -> {
          val matchResult = DISTRIBUTION_INSTRUCTION_REGEX.matchEntire(rawInstruction)!!.groupValues
          DistributionInstruction(
              botNumber = matchResult[1].toInt(),
              lowChipRecipient = DistributionRecipient(
                  type = DistributionRecipientType.fromString(matchResult[2]),
                  number = matchResult[3].toInt()),
              highChipRecipient = DistributionRecipient(
                  type = DistributionRecipientType.fromString(matchResult[4]),
                  number = matchResult[5].toInt()))
        }

        else -> { throw IllegalArgumentException("Instruction not recognized.") }
      }
    }
  }

}

data class ValueInstruction(val botNumber: Int, val chip: Int) : Instruction

data class DistributionInstruction(
    val botNumber: Int,
    val lowChipRecipient: DistributionRecipient,
    val highChipRecipient: DistributionRecipient) : Instruction
