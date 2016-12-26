fun main(args: Array<String>) {
  val whiteElephantGame = WhiteElephantGame(numberOfElves = 3005290)

  println("Part 1: ${whiteElephantGame.computeWinnerUsingV1Rules}")

//  // Run this loop to see an illustration of the pattern used to compute computeWinnerUsingV2Rules efficiently.
//  for (numberOfElves in 2..100) {
//    println("$numberOfElves elves -> elf ${WhiteElephantGame(numberOfElves).computeWinnerUsingV2Rules} wins")
//  }

  println("Part 2: ${whiteElephantGame.computeWinnerUsingV2Rules}")
}
