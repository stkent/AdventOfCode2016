enum class RotationDirection {
  LEFT,
  RIGHT;

  companion object {
    fun parse(string: String): RotationDirection {
      when (string) {
        "L"  -> return LEFT
        "R"  -> return RIGHT
        else -> throw IllegalArgumentException("Invalid rotation direction received.")
      }
    }
  }

}
