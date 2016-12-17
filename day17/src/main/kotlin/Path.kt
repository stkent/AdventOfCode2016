data class Path(val startLocation: Location, val doorSequence: List<Door> = emptyList()) {

  val lastLocation: Location by lazy {
    startLocation.copy(
        x = startLocation.x + doorSequence.count { it == Door.RIGHT } - doorSequence.count { it == Door.LEFT },
        y = startLocation.y + doorSequence.count { it == Door.DOWN } - doorSequence.count { it == Door.UP }
    )
  }

  val length: Int by lazy {
    doorSequence.size
  }

  override fun toString(): String {
    return doorSequence.map(Door::toString).joinToString(separator = "")
  }

}
