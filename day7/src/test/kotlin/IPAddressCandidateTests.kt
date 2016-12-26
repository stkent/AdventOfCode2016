import io.kotlintest.specs.BehaviorSpec

class IPAddressCandidateTests : BehaviorSpec() {

  init {
    Given("an IP address candidate with an ABBA sequence outside brackets and no ABBA sequence inside brackets") {
      val ipAddressCandidate = IPAddressCandidate("abba[mnop]qrst")

      When("I ask if this IP address candidate supports TLS") {
        val ipAddressSupportsTLS = ipAddressCandidate.supportsTransportLayerSnooping

        Then("the answer should be true") {
          ipAddressSupportsTLS shouldBe true
        }
      }
    }

    Given("an IP address candidate with an ABBA sequence outside brackets and an ABBA sequence inside brackets") {
      val ipAddressCandidate = IPAddressCandidate("abcd[bddb]xyyx")

      When("I ask if this IP address candidate supports TLS") {
        val ipAddressSupportsTLS = ipAddressCandidate.supportsTransportLayerSnooping

        Then("the answer should be false") {
          ipAddressSupportsTLS shouldBe false
        }
      }
    }

    Given("an IP address candidate with an invalid AAAA sequence outside brackets") {
      val ipAddressCandidate = IPAddressCandidate("aaaa[qwer]tyui")

      When("I ask if this IP address candidate supports TLS") {
        val ipAddressSupportsTLS = ipAddressCandidate.supportsTransportLayerSnooping

        Then("the answer should be false") {
          ipAddressSupportsTLS shouldBe false
        }
      }
    }

    Given("an IP address candidate with a nested ABBA sequence outside brackets and no ABBA sequence inside brackets") {
      val ipAddressCandidate = IPAddressCandidate("ioxxoj[asdfgh]zxcvbn")

      When("I ask if this IP address candidate supports TLS") {
        val ipAddressSupportsTLS = ipAddressCandidate.supportsTransportLayerSnooping

        Then("the answer should be true") {
          ipAddressSupportsTLS shouldBe true
        }
      }
    }

    Given("an IP address candidate with an ABA sequence outside brackets and the corresponding BAB sequence inside brackets") {
      val ipAddressCandidate = IPAddressCandidate("aba[bab]xyz")

      When("I ask if this IP address candidate supports SSL") {
        val ipAddressSupportsSSL = ipAddressCandidate.supportsSuperSecretListening

        Then("the answer should be true") {
          ipAddressSupportsSSL shouldBe true
        }
      }
    }

    Given("an IP address candidate with an ABA sequence outside brackets and no corresponding BAB sequence inside brackets") {
      val ipAddressCandidate = IPAddressCandidate("xyx[xyx]xyx")

      When("I ask if this IP address candidate supports SSL") {
        val ipAddressSupportsSSL = ipAddressCandidate.supportsSuperSecretListening

        Then("the answer should be false") {
          ipAddressSupportsSSL shouldBe false
        }
      }
    }

    Given("an IP address candidate with an invalid AAA sequence and a valid ABA sequence outside brackets and the corresponding BAB sequence inside brackets") {
      val ipAddressCandidate = IPAddressCandidate("aaa[kek]eke")

      When("I ask if this IP address candidate supports SSL") {
        val ipAddressSupportsSSL = ipAddressCandidate.supportsSuperSecretListening

        Then("the answer should be true") {
          ipAddressSupportsSSL shouldBe true
        }
      }
    }

    Given("an IP address candidate with multiple ABA sequences outside brackets and a corresponding BAB sequence inside brackets") {
      val ipAddressCandidate = IPAddressCandidate("zazbz[bzb]cdb")

      When("I ask if this IP address candidate supports SSL") {
        val ipAddressSupportsSSL = ipAddressCandidate.supportsSuperSecretListening

        Then("the answer should be true") {
          ipAddressSupportsSSL shouldBe true
        }
      }
    }
  }

}
