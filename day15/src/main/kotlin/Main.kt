class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val sculpture1 = Sculpture(listOf(
          Pair(Disc(numberOfPositions = 17), 5),
          Pair(Disc(numberOfPositions = 19), 8),
          Pair(Disc(numberOfPositions =  7), 1),
          Pair(Disc(numberOfPositions = 13), 7),
          Pair(Disc(numberOfPositions =  5), 1),
          Pair(Disc(numberOfPositions =  3), 0)
      ))

      var numberOfRotationsPerformed = 0

      while (!sculpture1.canCapsuleBeDropped()) {
        sculpture1.performOneRotation()
        numberOfRotationsPerformed += 1
      }

      println("Part 1: $numberOfRotationsPerformed")

      val sculpture2 = Sculpture(listOf(
          Pair(Disc(numberOfPositions = 17), 5),
          Pair(Disc(numberOfPositions = 19), 8),
          Pair(Disc(numberOfPositions =  7), 1),
          Pair(Disc(numberOfPositions = 13), 7),
          Pair(Disc(numberOfPositions =  5), 1),
          Pair(Disc(numberOfPositions =  3), 0),
          Pair(Disc(numberOfPositions = 11), 0)
      ))

      numberOfRotationsPerformed = 0

      while (!sculpture2.canCapsuleBeDropped()) {
        sculpture2.performOneRotation()
        numberOfRotationsPerformed += 1
      }

      println("Part 2: $numberOfRotationsPerformed")
    }
  }

}
