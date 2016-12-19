class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val input = 3005290

      var elvesWithPresents = (1..input).toList()
      println(elvesWithPresents)

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
        println(elvesWithPresents.size)
      }

      println("Part 1: ${elvesWithPresents.first()}")
    }
  }

}