class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val input = "^.^^^..^^...^.^..^^^^^.....^...^^^..^^^^.^^.^^^^^^^^.^^.^^^^...^^...^^^^.^.^..^^..^..^.^^.^.^......."

      println("Part 1: ${Room(input).computeNumberOfSafeTiles(numberOfRows = 40)}")

      /*
       * Optimization: if row i matches row j, then compute the sum from rows i until j and multiply an appropriate
       * number of times, finally adding the sum from the remaining rows. This would work because each row is deduced
       * using the previous row only.
       */
      println("Part 2: ${Room(input).computeNumberOfSafeTiles(numberOfRows = 400000)}")
    }
  }

}
