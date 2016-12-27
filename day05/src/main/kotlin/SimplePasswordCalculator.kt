import extensions.md5

data class SimplePasswordCalculator(val seed: String) {

  fun calculatePasswordOfLength(passwordLength: Int) : String {
    var result = ""
    var currentTestIndex = 0

    while (result.length < passwordLength) {
      val candidateHash = "$seed$currentTestIndex".md5()
      
      if (candidateHash.startsWith("00000")) {
        result = "$result${candidateHash[5]}"
      }

      currentTestIndex += 1
    }

    return result
  }
  
}