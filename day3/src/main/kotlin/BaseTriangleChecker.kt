import java.io.File

abstract class BaseTriangleChecker {

  abstract fun countNumberOfValidTriangles(inputFile: File): Int

  protected val spaceSplitRegex = Regex("\\s+")

  /**
   * Implicit assumptions:
   * 
   * - list size is always exactly 3
   * - all list elements are non-negative
   * 
   */
  fun isValidTriangle(sides: List<Int>): Boolean {
    val sortedSides = sides.sorted()
    return sortedSides[0] + sortedSides[1] > sortedSides[2]
  }

}
