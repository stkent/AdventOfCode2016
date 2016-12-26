class DiskDataGenerator(private val size: Int) {

  companion object {
    fun computeModifiedDragonCurve(source: String): String {
      val suffix = source.reversed()
          .replace(oldChar = '0', newChar = 'X')
          .replace(oldChar = '1', newChar = '0')
          .replace(oldChar = 'X', newChar = '1')

      return source + '0' + suffix
    }

    private fun computeChecksum(source: String): String {
      val resultStringList = mutableListOf<Char>()

      (0 until source.length step 2).mapTo(resultStringList) { index ->
        when (source.substring(index, index + 2)) {
          "11", "00" -> '1'
          "10", "01" -> '0'
          else -> throw IllegalStateException("This branch should never be executed!")
        }
      }

      return resultStringList.joinToString(separator = "")
    }
  }

  fun generateData(initialBits: String): Data {
    if (size <= 0) throw IllegalStateException("Disk size must be positive!")

    var generatedBits = initialBits

    while (generatedBits.length < size) {
      generatedBits = computeModifiedDragonCurve(generatedBits)
    }

    generatedBits = generatedBits.substring(0, size)

    var checkSum = generatedBits

    do {
      checkSum = computeChecksum(checkSum)
    } while (checkSum.length % 2 == 0)

    return Data(generatedBits, checkSum)
  }

}

data class Data(val bits: String, val checksum: String)
