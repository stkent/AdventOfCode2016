import io.kotlintest.specs.BehaviorSpec

class RoomCandidateTests : BehaviorSpec() {

  init {
    Given("a room candidate with name \"aaaaa-bbb-z-y-x-123[abxyz]\"") {
      val roomCandidate = RoomCandidate("aaaaa-bbb-z-y-x-123[abxyz]")

      When("I ask if it represents a real room") {
        val representsRealRoom = roomCandidate.isARealRoom()

        Then("the answer should be true") {
          representsRealRoom shouldBe true
        }
      }
    }

    Given("a room candidate with name \"a-b-c-d-e-f-g-h-987[abcde]\"") {
      val roomCandidate = RoomCandidate("a-b-c-d-e-f-g-h-987[abcde]")

      When("I ask if it represents a real room") {
        val representsRealRoom = roomCandidate.isARealRoom()

        Then("the answer should be true") {
          representsRealRoom shouldBe true
        }
      }
    }

    Given("a room candidate with name \"not-a-real-room-404[oarel]\"") {
      val roomCandidate = RoomCandidate("not-a-real-room-404[oarel]")

      When("I ask if it represents a real room") {
        val representsRealRoom = roomCandidate.isARealRoom()

        Then("the answer should be true") {
          representsRealRoom shouldBe true
        }
      }
    }

    Given("a room candidate with name \"totally-real-room-200[decoy]\"") {
      val roomCandidate = RoomCandidate("totally-real-room-200[decoy]")

      When("I ask if it represents a real room") {
        val representsRealRoom = roomCandidate.isARealRoom()

        Then("the answer should be false") {
          representsRealRoom shouldBe false
        }
      }
    }

    Given("a room candidate with name \"qzmt-zixmtkozy-ivhz-343[zimth]\"") {
      val roomCandidate = RoomCandidate("qzmt-zixmtkozy-ivhz-343[zimth]")

      When("I ask if it represents a real room") {
        val representsRealRoom = roomCandidate.isARealRoom()

        Then("the answer should be true") {
          representsRealRoom shouldBe true
        }
      }

      When("I ask for the decrypted room name") {
        val decryptedRoomName = roomCandidate.getDecryptedRoomName()

        Then("the answer should be \"very encrypted name\"") {
          decryptedRoomName shouldBe "very encrypted name"
        }
      }
    }
  }

}
