import java.io.File

class RowTriangleChecker : BaseTriangleChecker() {
  
  override fun countNumberOfValidTriangles(inputFile: File) : Int {
    var result = 0
    
    inputFile.readLines().forEach {
      val triangleSides = it.trim().split(spaceSplitRegex).map(String::toInt)

      if (isValidTriangle(triangleSides)) {
        result++
      }
    }
    
    return result
  }
  
}
