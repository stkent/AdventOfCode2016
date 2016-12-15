class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val router = Router(
          spacePredicate = { l ->
            val sum = l.x * l.x + 3 * l.x + 2 * l.x * l.y + l.y + l.y * l.y + 1358

            Integer
                .toBinaryString(sum)
                .groupBy { it }['1'].orEmpty()
                .size % 2 == 0
          }
      )

      println("Part 1: ${router.lengthOfShortestRouteBetween(Location(1, 1), Location(31, 39))}")
      println("Part 2: ${router.findNumberOfSpacesWithinNumberOfSteps(50, Location(1, 1))}")
    }
  }

}