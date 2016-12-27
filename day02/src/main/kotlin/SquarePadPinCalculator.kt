import java.lang.Math.max
import java.lang.Math.min

class SquarePadPinCalculator : BasePinCalculator() {

  // (0, 0) maps to key 1, and (2, 2) maps to key 9.
  private var coordinate = Pair(1, 1)

  override val key: String
    get() = ((coordinate.first + 1) + coordinate.second * 3).toString()

  override fun applyInstruction(char: Char) {
    when (char) {
      'R' -> coordinate = coordinate.copy(first  = min(coordinate.first  + 1, 2))
      'L' -> coordinate = coordinate.copy(first  = max(coordinate.first  - 1, 0))
      'U' -> coordinate = coordinate.copy(second = max(coordinate.second - 1, 0))
      'D' -> coordinate = coordinate.copy(second = min(coordinate.second + 1, 2))
    }
  }

}
