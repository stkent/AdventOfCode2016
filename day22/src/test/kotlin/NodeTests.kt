import io.kotlintest.specs.BehaviorSpec

class NodeTests : BehaviorSpec() {

  init {
    Given("the string \"/dev/grid/node-x1-y17    90T   72T    18T   80%\", which represents a node") {
      val string = "/dev/grid/node-x1-y17    90T   72T    18T   80%"

      When("I parse that string into a Node object") {
        val node = Node.parse(string)

        Then("the parsed Node object has all properties set correctly") {
          node.x           shouldBe  1
          node.y           shouldBe 17
          node.size        shouldBe 90
          node.used        shouldBe 72
          node.avail       shouldBe 18
          node.usedPercent shouldBe 80
        }
      }
    }
  }

}
