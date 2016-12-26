fun main(args: Array<String>) {
  val input = "10111011111001111"

  println("Part 1: ${DiskDataGenerator(size = 272).generateData(initialBits = input).checksum}")
  println("Part 2: ${DiskDataGenerator(size = 35651584).generateData(initialBits = input).checksum}")
}
