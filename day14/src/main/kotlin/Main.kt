fun main(args: Array<String>) {
  val input = "cuanljph"

  val keyGenerator = KeyGenerator(salt = input)

  println("Part 1: ${keyGenerator.generateKeys(64, applyKeyStretching = false).last()}")
  println("Part 2: ${keyGenerator.generateKeys(64, applyKeyStretching =  true).last()}")
}
