fun main(args: Array<String>) {
  val TARGET_REGEX = Regex("^(10)+$")
  val LOWER_BOUND = 2532
  var candidate = LOWER_BOUND

  while (true) {
    if (TARGET_REGEX.matchEntire(Integer.toBinaryString(candidate)) != null) {
      println("Part 1: ${candidate - LOWER_BOUND}")
      break
    }

    candidate += 1
  }
}
