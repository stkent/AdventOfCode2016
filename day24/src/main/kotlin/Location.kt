data class Location(val x: Int, val y: Int) {

  fun taxicabDistanceTo(location: Location): Int {
    return Math.abs(location.x - x) + Math.abs(location.y - y)
  }

  override fun toString(): String {
    return "($x, $y)"
  }

}
