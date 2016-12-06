fun main(args: Array<String>) {
  val seed = "ojvtpuvg"
  val passwordLength = 8

  println("Part 1: computed password = ${SimplePasswordCalculator(seed).calculatePasswordOfLength(passwordLength)}")
  println("Part 2: computed password = ${ComplexPasswordCalculator(seed).calculatePasswordOfLength(passwordLength)}")
}
