import java.io.File

class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)

      inputFile.forEachLine {
        println("Part 1: ${Decrypter(it).calculateLengthUsingAlgorithm1()}")
        println("Part 2: ${Decrypter(it).calculateLengthUsingAlgorithm2()}")
      }

    }
  }

}
