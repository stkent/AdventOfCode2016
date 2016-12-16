package extensions

import java.security.MessageDigest

fun String.allCharactersMatch(): Boolean {
  if (isEmpty()) {
    throw IllegalArgumentException("This method can only be called on non-empty Strings.")
  }

  return all { it == first() }
}

fun String.allCharactersMatch(char: Char): Boolean {
  if (isEmpty()) {
    throw IllegalArgumentException("This method can only be called on non-empty Strings.")
  }

  return all { it == char }
}

fun String.firstRepeatedChar(repetitions: Int): Char? {
  if (repetitions > length) {
    return null
  }

  (0..length - repetitions).forEach { startIndex ->
    if (substring(startIndex until startIndex + repetitions).allCharactersMatch()) {
      return get(startIndex)
    }
  }

  return null
}

fun String.containsRepeatedChar(char: Char, repetitions: Int): Boolean {
  if (repetitions > length) {
    return false
  }

  (0..length - repetitions).forEach { startIndex ->
    if (substring(startIndex until startIndex + repetitions).allCharactersMatch(char)) {
      return true
    }
  }

  return false
}

// Adapted from https://bitbucket.org/snippets/gelin/zLebo/extension-functions-to-format-bytes-as-hex:

fun String.md5() : String {
  // Could be optimized!
  return MessageDigest.getInstance("MD5").digest(toByteArray()).toHexString()
}
