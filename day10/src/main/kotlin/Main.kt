import java.io.File

class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)

      val factory = Factory()
      factory.processInstructions(inputFile.readLines())

      val outputs = factory.outputs
      println("Part 2: ${outputs[0]!! * outputs[1]!! * outputs[2]!!}")
    }
  }

}
