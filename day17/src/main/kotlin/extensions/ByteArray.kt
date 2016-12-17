package extensions

// Adapted from https://bitbucket.org/snippets/gelin/zLebo/extension-functions-to-format-bytes-as-hex:

fun ByteArray.toHexString() : String {
  val builder = StringBuilder()

  for (b in this) {
    builder.append(b.toHexString())
  }

  return builder.toString()
}
