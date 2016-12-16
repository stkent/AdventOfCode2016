import io.kotlintest.specs.BehaviorSpec

class SculptureTests : BehaviorSpec() {

  init {
    Given("a sculpture with disks of sizes 5 and 2 at initial positions 4 and 1") {
      When("I compute how many time steps I must wait to be able to successfully drop a capsule") {
        Then("I am correctly told I must wait 5 time steps") {
          val sculpture = Sculpture(listOf(
              Pair(Disc(numberOfPositions = 5), 4),
              Pair(Disc(numberOfPositions = 2), 1)
          ))

          var numberOfRotationsPerformed = 0

          while (!sculpture.canCapsuleBeDropped()) {
            sculpture.performOneRotation()
            numberOfRotationsPerformed += 1
          }

          numberOfRotationsPerformed shouldBe 5
        }
      }
    }
  }

}
