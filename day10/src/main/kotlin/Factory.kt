import DistributionRecipientType.BOT
import DistributionRecipientType.OUTPUT

class Factory {

  val indexedBots = mutableMapOf<Int, Bot>()

  // Note: based on the provided input structure, each output bin will only ever be assigned a single chip.
  val outputs = mutableMapOf<Int, Int>()

  fun processInstructions(rawInstructions: List<String>) {
    val instructions = rawInstructions.map { rawInstruction -> Instruction.parse(rawInstruction) }
    val valueInstructions = instructions.filterIsInstance<ValueInstruction>()
    val distributionInstructions = instructions.filterIsInstance<DistributionInstruction>()

    initializeBots(valueInstructions)
    distributeChips(distributionInstructions)
  }

  private fun initializeBots(valueInstructions: List<ValueInstruction>) {
    valueInstructions.forEach { instruction ->
      val botNumber = instruction.botNumber
      indexedBots.put(botNumber, (indexedBots[botNumber] ?: Bot()).addChip(instruction.chip))
    }
  }

  private fun distributeChips(distributionInstructions: List<DistributionInstruction>) {
    var nextBotNumberToProcess = getNextBotNumberToProcess()

    while (nextBotNumberToProcess != null) {
      val nextBotToProcess = indexedBots[nextBotNumberToProcess]!!

      val instruction = distributionInstructions
          .filter { instruction -> instruction.botNumber == nextBotNumberToProcess }
          .first() // If more than one instruction can be applied to the bot being processed, just pick the first one.

      if (nextBotToProcess.lowChip == 17 && nextBotToProcess.highChip == 61) {
        println("Part 1: $nextBotNumberToProcess")
      }

      distributeChip(chip = nextBotToProcess.lowChip,  distributionRecipient = instruction.lowChipRecipient)
      distributeChip(chip = nextBotToProcess.highChip, distributionRecipient = instruction.highChipRecipient)

      indexedBots.put(nextBotNumberToProcess, Bot())

      nextBotNumberToProcess = getNextBotNumberToProcess()
    }
  }

  // If more than one bot is ready for processing, just returns the bot with the lowest index.
  private fun getNextBotNumberToProcess(): Int? {
    return indexedBots
        .filter { indexedBot -> indexedBot.value.hasTwoChips }
        .keys
        .toSortedSet()
        .firstOrNull()
  }

  private fun distributeChip(chip: Int, distributionRecipient: DistributionRecipient) {
    when (distributionRecipient.type) {
      BOT -> {
        val recipientBotNumber = distributionRecipient.number
        indexedBots.put(recipientBotNumber, (indexedBots[recipientBotNumber] ?: Bot()).addChip(chip))
      }

      OUTPUT -> {
        val recipientOutputNumber = distributionRecipient.number
        outputs.put(recipientOutputNumber, chip)
      }
    }
  }

}
