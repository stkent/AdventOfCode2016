data class DistributionRecipient(val type: DistributionRecipientType, val number: Int)

enum class DistributionRecipientType {
  BOT, OUTPUT;

  companion object {
    fun fromString(string: String): DistributionRecipientType {
      return when (string) {
        "bot" -> BOT
        "output" -> OUTPUT
        else -> throw IllegalArgumentException("Instruction not recognized")
      }
    }
  }
}
