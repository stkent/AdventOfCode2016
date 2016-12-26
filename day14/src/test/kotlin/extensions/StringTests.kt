package extensions

import io.kotlintest.specs.BehaviorSpec

class StringTests : BehaviorSpec() {

  init {
    Given("a string in which all characters match") {
      val string = "aaaaa"

      When("I query whether all characters match") {
        val allCharactersMatch = string.allCharactersMatch()

        Then("I am correctly told that they do") {
          allCharactersMatch shouldBe true
        }
      }

      When("I query whether all characters match the repeated character") {
        val allCharactersMatchRepeatedCharacter = string.allCharactersMatch('a')

        Then("I am correctly told that they do") {
          allCharactersMatchRepeatedCharacter shouldBe true
        }
      }

      When("I query whether all characters match some other character") {
        val allCharactersMatchSomeOtherCharacter = string.allCharactersMatch('b')

        Then("I am correctly told that they do not") {
          allCharactersMatchSomeOtherCharacter shouldBe false
        }
      }
    }

    Given("a string that starts with a single character repeated 3 times, and no other characters are repeated") {
      val string = "aaabcde"

      When("I ask for the first character that is repeated at least 3 times") {
        val firstCharRepeatedAtLeast3Times = string.firstRepeatedChar(minRepetitions = 3)

        Then("the correct character should be identified") {
          firstCharRepeatedAtLeast3Times shouldBe 'a'
        }
      }
    }

    Given("a string with a single character repeated 3 times in the middle, and no other characters are repeated") {
      val string = "abcccde"

      When("I ask for the first character that is repeated at least 3 times") {
        val firstCharRepeatedAtLeast3Times = string.firstRepeatedChar(minRepetitions = 3)

        Then("the correct character should be identified") {
          firstCharRepeatedAtLeast3Times shouldBe 'c'
        }
      }
    }

    Given("a string that ends with a single character repeated 3 times, and no other characters are repeated") {
      val string = "abcdeee"

      When("I ask for the first character that is repeated at least 3 times") {
        val firstCharRepeatedAtLeast3Times = string.firstRepeatedChar(minRepetitions = 3)

        Then("the correct character should be identified") {
          firstCharRepeatedAtLeast3Times shouldBe 'e'
        }
      }
    }

    Given("a string that starts with a single character repeated 3 times") {
      val string = "aaabbbccc"

      When("I ask if the string contains that character repeated at least 3 times") {
        val containsSpecifiedCharRepeatedAtLeast3Times = string.containsRepeatedChar('a', minRepetitions = 3)

        Then("the result should be true") {
          containsSpecifiedCharRepeatedAtLeast3Times shouldBe true
        }
      }
    }

    Given("a string that has a single character repeated 3 times in the middle") {
      val string = "aaabbbccc"

      When("I ask if the string contains that character repeated at least 3 times") {
        val containsSpecifiedCharRepeatedAtLeast3Times = string.containsRepeatedChar('b', minRepetitions = 3)

        Then("the result should be true") {
          containsSpecifiedCharRepeatedAtLeast3Times shouldBe true
        }
      }
    }

    Given("a string that ends with a single character repeated 3 times") {
      val string = "aaabbbccc"

      When("I ask if the string contains that character repeated at least 3 times") {
        val containsSpecifiedCharRepeatedAtLeast3Times = string.containsRepeatedChar('c', minRepetitions = 3)

        Then("the result should be true") {
          containsSpecifiedCharRepeatedAtLeast3Times shouldBe true
        }
      }
    }

    Given("a string that contains at least one substring of length at least 3 consisting of a single repeated character") {
      val string = "aaabcde"

      When("I asked if the string contains that character repeated at least 2 times") {
        val containsSpecifiedCharRepeatedAtLeast2Times = string.containsRepeatedChar('a', minRepetitions = 2)

        Then("the result should be true") {
          containsSpecifiedCharRepeatedAtLeast2Times shouldBe true
        }
      }
    }

    Given("a string that contains no substrings of length at least 4 consisting of a single repeated character") {
      val string = "aaabcdeaaa"

      When("I asked if any of the string characters appear in four consecutive locations") {
        val containsSequenceOf4ConsecutiveChars = string
            .toCharArray()
            .distinct()
            .map { string.containsRepeatedChar(it, minRepetitions = 4) }
            .fold(initial = false) { b1, b2 -> b1 || b2 }

        Then("the result should be false") {
          containsSequenceOf4ConsecutiveChars shouldBe false
        }
      }
    }

    Given("the first sample string from the puzzle statement") {
      val string = "347dac6ee8eeea4652c7476d0f97bee5"

      When("I ask for the first character repeated at least 3 times") {
        val firstCharRepeatedAtLeast3Times = string.firstRepeatedChar(minRepetitions = 3)

        Then("the result should be the character 'e'") {
          firstCharRepeatedAtLeast3Times shouldBe 'e'
        }
      }
    }

    Given("the second sample string from the puzzle statement") {
      val string = "3aeeeee1367614f3061d165a5fe3cac3"

      When("I ask for the first character repeated at least 5 times") {
        val firstCharRepeatedAtLeast5Times = string.firstRepeatedChar(minRepetitions = 5)

        Then("the result should be the character 'e'") {
          firstCharRepeatedAtLeast5Times shouldBe 'e'
        }
      }
    }
  }

}
