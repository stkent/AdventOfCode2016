import extensions.pow

class WhiteElephantGame(private val numberOfElves: Int) {

  val computeWinnerUsingV1Rules: Int by lazy {
    var elvesWithPresents = (1..numberOfElves).toList()

    while (elvesWithPresents.size > 1) {
      val updatedElvesWithPresents = mutableListOf<Int>()

      (0 until elvesWithPresents.size step 2).mapTo(updatedElvesWithPresents) { index ->
        elvesWithPresents[index]
      }

      if (elvesWithPresents.size % 2 == 1) {
        updatedElvesWithPresents.removeAt(updatedElvesWithPresents.lastIndex)
        updatedElvesWithPresents.add(0, elvesWithPresents.last())
      }

      elvesWithPresents = updatedElvesWithPresents
    }

    elvesWithPresents.first()
  }

  // See commented-out code in Main.kt to understand this implementation!
  val computeWinnerUsingV2Rules: Int by lazy {
    var smallestPowerOf3LessThanNumberOfElves = 0

    while (3.pow(smallestPowerOf3LessThanNumberOfElves + 1) < numberOfElves) {
      smallestPowerOf3LessThanNumberOfElves += 1
    }

    val threshold = 3.pow(smallestPowerOf3LessThanNumberOfElves)
    val remainderNumberOfElves = numberOfElves - threshold

    if (remainderNumberOfElves < threshold) {
      remainderNumberOfElves
    } else {
      threshold + 2 * (remainderNumberOfElves - threshold)
    }
  }

}
