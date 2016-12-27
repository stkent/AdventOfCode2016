import extensions.nonNegativeMod

enum class Orientation {
  NORTH,
  EAST,
  SOUTH,
  WEST;

  fun turnRight(): Orientation {
    return values()[(ordinal + 1).nonNegativeMod(4)]
  }

  fun turnLeft(): Orientation {
    return values()[(ordinal - 1).nonNegativeMod(4)]
  }

}
