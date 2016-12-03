import java.io.File

class ColumnTriangleChecker : BaseTriangleChecker() {

  override fun countNumberOfValidTriangles(inputFile: File): Int {
    var result = 0

    val inputRowInts = inputFile.readLines().map { it.trim().split(spaceSplitRegex).map(String::toInt) }

    for (baseRow in 0 until inputRowInts.size step 3) {
      for (column in 0..2) {
        val triangleSides = mutableListOf<Int>()

        (0..2).mapTo(triangleSides) { rowOffset ->
          inputRowInts[baseRow + rowOffset][column]
        }

        if (isValidTriangle(triangleSides)) {
          result++
        }
      }
    }

    return result
  }

}
