import java.io.File

class Main {

  companion object {

    private val LARGEST_VALID_ADDRESS: Long = 4294967295

    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)

      val blacklistedAddresses = inputFile.readLines()
          .map { line ->
            line.split('-').map(String::toLong)
          }
          .map {
            it[0]..it[1]
          }

      val addressChecker = AddressChecker(blacklistedAddresses)

      val lowestValidAddress = addressChecker.findFirstValidAddress(0..LARGEST_VALID_ADDRESS)
      println("Part 1: $lowestValidAddress")

      val numberOfValidAddresses = addressChecker.computeNumberOfValidAddresses(0..LARGEST_VALID_ADDRESS)
      println("Part 2: $numberOfValidAddresses")
    }
  }

}
