package extensions

// Adapted from https://bitbucket.org/snippets/gelin/zLebo/extension-functions-to-format-bytes-as-hex:

private val HEX_CHARS = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

fun Byte.toHexString() : String {
  val thisAsInt = this.toInt()
  val resultChar2 = HEX_CHARS[thisAsInt and 0x0f]
  val resultChar1 = HEX_CHARS[thisAsInt shr 4 and 0x0f]
  return "$resultChar1$resultChar2"
}
