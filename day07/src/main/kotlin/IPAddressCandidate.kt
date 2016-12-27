data class IPAddressCandidate(val rawString: String) {

  val supportsTransportLayerSnooping: Boolean by lazy {
    val addressComponents = rawString.split(regex = Regex("[\\[\\]]"))

    val anySupernetSequenceContainsAbbaSequence = (0 until addressComponents.size step 2)
        .map { addressComponents[it].containsAbbaSequence() }
        .fold(false) { b1, b2 -> b1 || b2 }

    if (anySupernetSequenceContainsAbbaSequence) {
      val anyHypernetSequenceContainsAbbaSequence = (1 until addressComponents.size step 2)
          .map { addressComponents[it].containsAbbaSequence() }
          .fold(false) { b1, b2 -> b1 || b2 }

      anySupernetSequenceContainsAbbaSequence && !anyHypernetSequenceContainsAbbaSequence
    } else {
      false
    }
  }

  val supportsSuperSecretListening: Boolean by lazy {
    val addressComponents = rawString.split(regex = Regex("[\\[\\]]"))

    val supernetAbaSequences = (0 until addressComponents.size step 2)
        .map { addressComponents[it].getAbaSequences() }
        .toSet()
        .flatten()

    if (supernetAbaSequences.isNotEmpty()) {
      val hypernetBabSequences = (1 until addressComponents.size step 2)
          .map { addressComponents[it].getBabSequences() }
          .toSet()
          .flatten()

      supernetAbaSequences.intersect(hypernetBabSequences.map { it.correspondingAbaSequence }).isNotEmpty()
    } else {
      false
    }
  }

}

private data class AbaSequence(val a: Char, val b: Char) {

  override fun toString(): String {
    return "$a$b$a"
  }

}

private data class BabSequence(val b: Char, val a: Char) {

  val correspondingAbaSequence: AbaSequence by lazy {
    AbaSequence(a = a, b = b)
  }

  override fun toString(): String {
    return "$b$a$b"
  }

}

private fun String.containsAbbaSequence(): Boolean {
  if (length < 4) {
    return false
  }

  return (0..length - 4)
      .map { substring(it, it + 4) }
      .map { it[0] == it[3] && it[1] == it[2] && it[0] != it[1] }
      .fold(false) { b1, b2 -> b1 || b2 }
}

private fun String.getAbaSequences(): Set<AbaSequence> {
  if (length < 3) {
    return emptySet()
  }

  return (0..length - 3)
      .map { substring(it, it + 3) }
      .filter { it[0] == it[2] && it[0] != it[1] }
      .map { AbaSequence(a = it[0], b = it[1]) }
      .toSet()
}

private fun String.getBabSequences(): Set<BabSequence> {
  if (length < 3) {
    return emptySet()
  }

  return (0..length - 3)
      .map { substring(it, it + 3) }
      .filter { it[0] == it[2] && it[0] != it[1] }
      .map { BabSequence(b = it[0], a = it[1]) }
      .toSet()
}
