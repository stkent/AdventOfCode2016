import java.io.File

class Main {
  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)
      
      println("Part 1: number of valid triangles = ${RowTriangleChecker().countNumberOfValidTriangles(inputFile)}")
      println("Part 2: number of valid triangles = ${ColumnTriangleChecker().countNumberOfValidTriangles(inputFile)}")
    }
  }
}
