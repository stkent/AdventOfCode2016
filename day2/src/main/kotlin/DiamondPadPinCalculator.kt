import java.lang.Math.*

class DiamondPadPinCalculator : BasePinCalculator() {

  // (0, 0) maps to key 7, and (1, 1) maps to key C.
  private var coordinate = Pair(-2, 0)

  override val key: String
    get() {
      when (coordinate) {
        Pair( 0, -2) -> return "1"
        Pair(-1, -1) -> return "2"
        Pair( 0, -1) -> return "3"
        Pair( 1, -1) -> return "4"
        Pair(-2,  0) -> return "5"
        Pair(-1,  0) -> return "6"
        Pair( 0,  0) -> return "7"
        Pair( 1,  0) -> return "8"
        Pair( 2,  0) -> return "9"
        Pair(-1,  1) -> return "A"
        Pair( 0,  1) -> return "B"
        Pair( 1,  1) -> return "C"
        Pair( 0,  2) -> return "D"
        else -> throw IllegalStateException("Coordinate does not correspond to a valid key.")
      }
    }

  override fun applyInstruction(char: Char) {
    when (char) {
      'R' -> coordinate = coordinate.copy(first  = min(coordinate.first  + 1, 2 - abs(coordinate.second)))
      'L' -> coordinate = coordinate.copy(first  = max(coordinate.first  - 1, abs(coordinate.second) - 2))
      'U' -> coordinate = coordinate.copy(second = max(coordinate.second - 1, abs(coordinate.first) - 2))
      'D' -> coordinate = coordinate.copy(second = min(coordinate.second + 1, 2 - abs(coordinate.first)))
    }
  }

}
