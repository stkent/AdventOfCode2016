package extensions

import io.kotlintest.specs.BehaviorSpec

class String : BehaviorSpec() {

  init {
    Given("a string in which all characters match") {
      When("I query whether all characters match") {
        Then("I am correctly told that they do") {
          "aaaaa".allCharactersMatch() shouldBe true
        }
      }

      When("I query whether all characters match that character") {
        Then("I am correctly told that they do") {
          "aaaaa".allCharactersMatch('a') shouldBe true
        }
      }

      When("I query whether all characters match some other character") {
        Then("I am correctly told that they do not") {
          "aaaaa".allCharactersMatch('b') shouldBe false
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          "aaabbcc".firstRepeatedChar(3) shouldBe 'a'
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          "aabbbcc".firstRepeatedChar(3) shouldBe 'b'
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          "aabbccc".firstRepeatedChar(3) shouldBe 'c'
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          "aaabbbccc".containsRepeatedChar('a', 3) shouldBe true
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          "aaabbbccc".containsRepeatedChar('b', 3) shouldBe true
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          "aaabbbccc".containsRepeatedChar('c', 3) shouldBe true
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          "aaabbbccc".containsRepeatedChar('a', 2) shouldBe true
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          "aaabbbccc".containsRepeatedChar('a', 4) shouldBe false
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          "347dac6ee8eeea4652c7476d0f97bee5".firstRepeatedChar(3) shouldBe 'e'
        }
      }
    }

    Given("") {
      When("") {
        Then("") {
          "3aeeeee1367614f3061d165a5fe3cac3".containsRepeatedChar('e', 5) shouldBe true
        }
      }
    }
  }

}