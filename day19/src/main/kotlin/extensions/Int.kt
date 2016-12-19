package extensions

fun Int.pow(power: Int): Int {
  return Math.pow(toDouble(), power.toDouble()).toInt()
}
