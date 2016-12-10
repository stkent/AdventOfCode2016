data class Bot(val chips: List<Int> = listOf()) {

  fun addChip(chip: Int): Bot {
    if (hasTwoChips) { throw IllegalStateException("This bot is already full!") }

    val newChips = mutableListOf<Int>()
    newChips.addAll(chips)
    newChips.add(chip)
    return Bot(newChips)
  }

  val hasTwoChips: Boolean
    get() = chips.size == 2

  val lowChip: Int
    get() {
      if (!hasTwoChips) { throw IllegalStateException("This bot is not full yet!") }
      return chips.min()!!
    }

  val highChip: Int
    get() {
      if (!hasTwoChips) { throw IllegalStateException("This bot is not full yet!") }
      return chips.max()!!
    }

}
