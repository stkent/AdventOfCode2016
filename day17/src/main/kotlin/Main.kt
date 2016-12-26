fun main(args: Array<String>) {
  val input = "yjjvjgan"

  val router = Router(salt = input)

  println("Part 1: ${router.shortestRouteToVault()}")
  println("Part 2: ${router.lengthOfLongestRouteToVault()}")
}
