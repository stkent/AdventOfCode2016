fun main(args: Array<String>) {
  val seed = "ojvtpuvg"
  val passwordLength = 8

  println("Part 1: ${SimplePasswordCalculator(seed).calculatePasswordOfLength(passwordLength)}")
  println("Part 2: ${ComplexPasswordCalculator(seed).calculatePasswordOfLength(passwordLength)}")
}
