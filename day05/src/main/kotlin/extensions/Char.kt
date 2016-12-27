package extensions

fun Char.asIntOrNull() : Int? {
  if (!isDigit()) {
    return null
  }

  return toString().toInt()
}
