import extensions.nonNegativeMod

interface Instruction {

  companion object {
    fun parse(string: String): Instruction? {
      when {
        string.matches(SwapPositionsInstruction.RAW_STRING_REGEX) -> {
          val matchResult = SwapPositionsInstruction.RAW_STRING_REGEX.matchEntire(string)!!
          return SwapPositionsInstruction(matchResult.groupValues[1].toInt(), matchResult.groupValues[2].toInt())
        }

        string.matches(SwapLettersInstruction.RAW_STRING_REGEX) -> {
          val matchResult = SwapLettersInstruction.RAW_STRING_REGEX.matchEntire(string)!!
          return SwapLettersInstruction(matchResult.groupValues[1].first(), matchResult.groupValues[2].first())
        }

        string.matches(RotateByAmountInstruction.RAW_STRING_REGEX) -> {
          val matchResult = RotateByAmountInstruction.RAW_STRING_REGEX.matchEntire(string)!!
          val rotationDirectionSign = if (matchResult.groupValues[1] == "left") -1 else +1
          return RotateByAmountInstruction(rotationDirectionSign * matchResult.groupValues[2].toInt())
        }

        string.matches(RotateByCharPositionInstruction.RAW_STRING_REGEX) -> {
          val matchResult = RotateByCharPositionInstruction.RAW_STRING_REGEX.matchEntire(string)!!
          return RotateByCharPositionInstruction(matchResult.groupValues[1].first())
        }

        string.matches(ReverseInstruction.RAW_STRING_REGEX) -> {
          val matchResult = ReverseInstruction.RAW_STRING_REGEX.matchEntire(string)!!
          return ReverseInstruction(matchResult.groupValues[1].toInt(), matchResult.groupValues[2].toInt())
        }

        string.matches(MoveInstruction.RAW_STRING_REGEX) -> {
          val matchResult = MoveInstruction.RAW_STRING_REGEX.matchEntire(string)!!
          return MoveInstruction(matchResult.groupValues[1].toInt(), matchResult.groupValues[2].toInt())
        }

        else -> return null
      }
    }
  }

  fun apply(string: String): String

  fun invert() : Instruction

}

class SwapPositionsInstruction(private val x: Int, private val y: Int) : Instruction {

  companion object {
    val RAW_STRING_REGEX = Regex("swap position (\\d) with position (\\d)")
  }

  override fun apply(string: String): String {
    val xCharacter = string[x]
    val yCharacter = string[y]
    return string.replace(xCharacter, ' ').replace(yCharacter, xCharacter).replace(' ', yCharacter)
  }

  override fun invert(): Instruction {
    return this
  }

}

class SwapLettersInstruction(private val x: Char, private val y: Char) : Instruction {

  companion object {
    val RAW_STRING_REGEX = Regex("swap letter (\\w) with letter (\\w)")
  }

  override fun apply(string: String): String {
    return string.replace(x, ' ').replace(y, x).replace(' ', y)
  }

  override fun invert(): Instruction {
    return this
  }

}

class RotateByAmountInstruction(private val x: Int) : Instruction {

  companion object {
    val RAW_STRING_REGEX = Regex("rotate (left|right) (\\d) steps?")
  }

  override fun apply(string: String): String {
    val stringLength = string.length
    val forwardRotationAmount = x.nonNegativeMod(stringLength)
    val splitIndex = stringLength - forwardRotationAmount

    return string.substring(splitIndex) + string.substring(0, splitIndex)
  }

  override fun invert(): Instruction {
    return RotateByAmountInstruction(x = -x)
  }

}

class ReverseInstruction(private val x: Int, private val y: Int) : Instruction {

  companion object {
    val RAW_STRING_REGEX = Regex("reverse positions (\\d) through (\\d)")
  }

  override fun apply(string: String): String {
    return string.substring(0, x) + string.substring(x, y + 1).reversed() + string.substring(y + 1)
  }

  override fun invert(): Instruction {
    return this
  }

}

class MoveInstruction(private val x: Int, private val y: Int) : Instruction {

  companion object {
    val RAW_STRING_REGEX = Regex("move position (\\d) to position (\\d)")
  }

  override fun apply(string: String): String {
    val characterBeingMoved = string[x]
    val stringWithCharacterRemoved = string.substring(0, x) + string.substring(x + 1)
    return stringWithCharacterRemoved.substring(0, y) + characterBeingMoved + stringWithCharacterRemoved.substring(y)
  }

  override fun invert(): Instruction {
    return MoveInstruction(x = y, y = x)
  }

}

abstract class BaseRotateInstruction(
    private val x: Char,
    private val indexToRotationAmountMap: Map<Int, Int>) : Instruction {

  override fun apply(string: String): String {
    val firstIndexOfChar = string.indexOf(x)
    if (firstIndexOfChar == -1) return string

    val stringLength = string.length
    val forwardRotationAmount = indexToRotationAmountMap[firstIndexOfChar]!!.nonNegativeMod(stringLength)
    val splitIndex = stringLength - forwardRotationAmount

    return string.substring(splitIndex) + string.substring(0, splitIndex)
  }

}

/*
 * Note: Specialized for passwords of length 8. The rule given to map character indices to rotation amounts is not
 * invertible in general.
 */
class RotateByCharPositionInstruction(private var x: Char) : BaseRotateInstruction(
    x = x,
    /*
     * The result of applying the following rule to the indices 0 to 7 inclusive:
     *
     *   "rotate the string to the right one time, plus a number of times equal to that index, plus one additional time
     *    if the index was at least 4"
     */
    indexToRotationAmountMap =  mapOf(
        Pair(0, 1),
        Pair(1, 2),
        Pair(2, 3),
        Pair(3, 4),
        Pair(4, 6),
        Pair(5, 7),
        Pair(6, 8),
        Pair(7, 9)
    )) {

  companion object {
    val RAW_STRING_REGEX = Regex("rotate based on position of letter (\\w)")
  }

  override fun invert(): Instruction {
    return InvertedRotateByCharPositionInstruction(x)
  }

}

/*
 * Note: Specialized for passwords of length 8. The rule given to map character indices to rotation amounts is not
 * invertible in general.
 */
class InvertedRotateByCharPositionInstruction(private val x: Char) : BaseRotateInstruction(
    x = x,
    indexToRotationAmountMap =  mapOf(
        Pair(0, -9),
        Pair(1, -1),
        Pair(2, -6),
        Pair(3, -2),
        Pair(4, -7),
        Pair(5, -3),
        Pair(6, -8),
        Pair(7, -4)
    )) {

  override fun invert(): Instruction {
    return RotateByCharPositionInstruction(x)
  }

}
