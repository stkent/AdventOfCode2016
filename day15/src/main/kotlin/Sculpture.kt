data class Sculpture(var initialDiscStates: List<Pair<Disc, Int>>) {

  private val discs: List<Disc>

  private val discPositions: MutableList<Int>

  init {
    initialDiscStates.forEach { discState ->
      if (discState.second < 0 || discState.second >= discState.first.numberOfPositions) {
        throw IllegalStateException("Disc has an invalid initial position.")
      }
    }

    discs = initialDiscStates.map { it.first }
    discPositions = initialDiscStates.map { it.second }.toMutableList()
  }

  fun performOneRotation() {
    (0 until discs.size).forEach { discNumber ->
      discPositions[discNumber] = (discPositions[discNumber] + 1) % discs[discNumber].numberOfPositions
    }
  }

  fun canCapsuleBeDropped(): Boolean {
    return (0 until discs.size).all { discNumber ->
      (discPositions[discNumber] + discNumber + 1) % discs[discNumber].numberOfPositions == 0
    }
  }

}
