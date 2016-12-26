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

fun String.firstRepeatedChar(minRepetitions: Int): Char? {
  if (minRepetitions > length) {
    return null
  }

  (0..length - minRepetitions).forEach { startIndex ->
    if (substring(startIndex until startIndex + minRepetitions).allCharactersMatch()) {
      return get(startIndex)
    }
  }

  return null
}

fun String.containsRepeatedChar(char: Char, minRepetitions: Int): Boolean {
  if (minRepetitions > length) {
    return false
  }

  (0..length - minRepetitions).forEach { startIndex ->
    if (substring(startIndex until startIndex + minRepetitions).allCharactersMatch(char)) {
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
