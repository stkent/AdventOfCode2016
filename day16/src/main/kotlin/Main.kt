class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      // todo: cleanup!
      val input = "10111011111001111"

//      val targetDiscLength = 20
      val targetDiscLength = 35651584

      var generatedBits = input

      while (generatedBits.length < targetDiscLength) {
        generatedBits = computeModifiedDragonCurve(generatedBits)
      }

      var checkSum = generatedBits.substring(0, targetDiscLength)

      do {
        checkSum = computeChecksum(checkSum)
      } while (checkSum.length % 2 == 0)

      println("Part 1: $checkSum")
    }

    fun computeModifiedDragonCurve(source: String): String {
      val suffix = source.reversed()
          .replace(oldChar = '0', newChar = 'X')
          .replace(oldChar = '1', newChar = '0')
          .replace(oldChar = 'X', newChar = '1')

      return source + '0' + suffix
    }

    fun computeChecksum(source: String): String {
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

}
