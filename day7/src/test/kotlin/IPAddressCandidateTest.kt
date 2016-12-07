import io.kotlintest.specs.BehaviorSpec

class IPAddressCandidateTest : BehaviorSpec() {

  init {
    Given("") {
      When("") {
        Then("") {
          IPAddressCandidate("abba[mnop]qrst").supportsTransportLayerSnooping shouldBe true
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          IPAddressCandidate("abcd[bddb]xyyx").supportsTransportLayerSnooping shouldBe false
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          IPAddressCandidate("aaaa[qwer]tyui").supportsTransportLayerSnooping shouldBe false
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          IPAddressCandidate("ioxxoj[asdfgh]zxcvbn").supportsTransportLayerSnooping shouldBe true
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          IPAddressCandidate("aba[bab]xyz").supportsSuperSecretListening shouldBe true
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          IPAddressCandidate("xyx[xyx]xyx").supportsSuperSecretListening shouldBe false
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          IPAddressCandidate("aaa[kek]eke").supportsSuperSecretListening shouldBe true
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          IPAddressCandidate("zazbz[bzb]cdb").supportsSuperSecretListening shouldBe true
        }
      }
    }
  }

}
