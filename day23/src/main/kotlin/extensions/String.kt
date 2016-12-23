package extensions

fun String.isInt(): Boolean {
  try {
    toInt()
    return true
  } catch (ignored: NumberFormatException) {
    return false
  }
}
