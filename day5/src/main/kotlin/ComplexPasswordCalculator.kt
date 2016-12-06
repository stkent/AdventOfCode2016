import extensions.asIntOrNull
import extensions.md5

data class ComplexPasswordCalculator(val seed: String) {

  fun calculatePasswordOfLength(passwordLength: Int) : String {
    val result = mutableMapOf<Int, Char>().toSortedMap()
    var currentTestIndex = 0

    while (result.size < passwordLength) {
      val computedHash = "$seed$currentTestIndex".md5()
      
      if (computedHash.startsWith("00000")) {
        val sixthCharacterDigit = computedHash[5].asIntOrNull()
        
        sixthCharacterDigit?.let { 
          if (sixthCharacterDigit < passwordLength && !result.containsKey(sixthCharacterDigit)) {
            result.put(sixthCharacterDigit, computedHash[6])
          }
        }
      }

      currentTestIndex += 1
    }

    return result.values.joinToString(separator = "")
  }
  
}