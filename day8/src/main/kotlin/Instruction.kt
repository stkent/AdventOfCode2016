interface Instruction {

  companion object {
    fun parse(instructionString: String): Instruction {
      val numbers = instructionString
          .split(Regex("[^\\d]+"))
          .filter(String::isNotBlank)
          .map(String::toInt)

      when {
        instructionString.startsWith("rect")
            -> return RectInstruction(rectWidth = numbers[0], rectHeight = numbers[1])

        instructionString.startsWith("rotate row")
            -> return RotateRowInstruction(rowNumber = numbers[0], rotationAmount = numbers[1])

        instructionString.startsWith("rotate column")
            -> return RotateColumnInstruction(columnNumber = numbers[0], rotationAmount = numbers[1])
      }

      throw IllegalArgumentException("Instruction string is not recognized!")
    }
  }
}

data class RectInstruction(val rectWidth: Int, val rectHeight: Int) : Instruction

data class RotateColumnInstruction(val columnNumber: Int, val rotationAmount: Int) : Instruction

data class RotateRowInstruction(val rowNumber: Int, val rotationAmount: Int) : Instruction
