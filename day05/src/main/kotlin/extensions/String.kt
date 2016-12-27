package extensions

import java.security.MessageDigest

// Adapted from https://bitbucket.org/snippets/gelin/zLebo/extension-functions-to-format-bytes-as-hex:

fun String.md5() : String {
  // Could be optimized!
  return MessageDigest.getInstance("MD5").digest(toByteArray()).toHexString()
}
