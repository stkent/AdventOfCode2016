class AddressChecker(private val blacklistedAddresses: List<LongRange>) {

  fun findFirstValidAddress(candidateAddresses: LongRange): Long? {
    // If the list of candidate addresses is empty, return null.
    var result = candidateAddresses.firstOrNull() ?: return null

    while (blacklistedAddresses.any { it.contains(result)} ) {
      result = blacklistedAddresses
          .filter { it.contains(result) }
          .first()
          .last + 1
    }

    // If we've iterated beyond the last candidate address without terminating, then we never found a valid address.
    return if (result > candidateAddresses.last) null else result
  }

  fun computeNumberOfValidAddresses(candidateAddresses: LongRange) : Long {
    val nextValidAddressRangeStart = findFirstValidAddress(candidateAddresses) ?: return 0

    var currentAddress = nextValidAddressRangeStart

    if (currentAddress == candidateAddresses.last) return 1

    while (blacklistedAddresses.none { it.contains(currentAddress + 1)} ) {
      currentAddress += 1
    }

    return currentAddress - nextValidAddressRangeStart + 1 +
        computeNumberOfValidAddresses(candidateAddresses = currentAddress + 1..candidateAddresses.last)
  }

}
