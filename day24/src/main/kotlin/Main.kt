import java.io.File

class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)

      val ductMap = DuctMap(rows = inputFile.readLines())

      println("Part 1: ${Router(ductMap).computeLengthOfShortestRoutePassingThroughAllPOIs(mustReturnToInitialPOI = false)}")
      println("Part 2: ${Router(ductMap).computeLengthOfShortestRoutePassingThroughAllPOIs(mustReturnToInitialPOI = true)}")
    }
  }

}
