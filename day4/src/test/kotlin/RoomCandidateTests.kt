import io.kotlintest.specs.BehaviorSpec

class RoomCandidateTests : BehaviorSpec() {

  init {
    Given("the room name string \"aaaaa-bbb-z-y-x-123[abxyz]\"") {
      When("I ask if it represents a real room") {
        Then("the answer should be \"true\"") {
          RoomCandidate("aaaaa-bbb-z-y-x-123[abxyz]").isARealRoom() shouldBe true
        }
      }
    }

    Given("the room name string \"a-b-c-d-e-f-g-h-987[abcde]\"") {
      When("I ask if it represents a real room") {
        Then("the answer should be \"true\"") {
          RoomCandidate("a-b-c-d-e-f-g-h-987[abcde]").isARealRoom() shouldBe true
        }
      }
    }

    Given("the room name string \"not-a-real-room-404[oarel]\"") {
      When("I ask if it represents a real room") {
        Then("the answer should be \"true\"") {
          RoomCandidate("not-a-real-room-404[oarel]").isARealRoom() shouldBe true
        }
      }
    }

    Given("the room name string \"totally-real-room-200[decoy]\"") {
      When("I ask if it represents a real room") {
        Then("the answer should be \"false\"") {
          RoomCandidate("totally-real-room-200[decoy]").isARealRoom() shouldBe false
        }
      }
    }

    Given("the room name string \"qzmt-zixmtkozy-ivhz-343[zimth]\"") {
      When("I ask if it represents a real room") {
        Then("the answer should be \"true\"") {
          RoomCandidate("qzmt-zixmtkozy-ivhz-343[zimth]").isARealRoom() shouldBe true
        }
      }

      When("I ask for the decoded room name") {
        Then("the answer should be \"very encrypted name\"") {
          RoomCandidate("qzmt-zixmtkozy-ivhz-343[zimth]").getDecryptedRoomName() shouldBe "very encrypted name"
        }
      }
    }
  }

}