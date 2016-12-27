import java.io.File

class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)

      println("Part 1: ${RowTriangleChecker().countNumberOfValidTriangles(inputFile)}")
      println("Part 2: ${ColumnTriangleChecker().countNumberOfValidTriangles(inputFile)}")
    }
  }

}
