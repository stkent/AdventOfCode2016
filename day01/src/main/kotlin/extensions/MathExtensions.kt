package extensions

fun Int.nonNegativeMod(other: Int): Int {
  var result = mod(other)

  if (result < 0) {
    result += other
  }

  return result
}
