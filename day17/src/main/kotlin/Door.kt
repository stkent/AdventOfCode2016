enum class Door {
  UP, DOWN, LEFT, RIGHT;

  override fun toString(): String {
    return when (this) {
      UP -> "U"
      DOWN -> "D"
      LEFT -> "L"
      RIGHT -> "R"
    }
  }

}
