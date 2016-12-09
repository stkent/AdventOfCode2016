class Display(rows: Int, columns: Int) {

  companion object {
    private val PIXEL_OFF_CHAR = '.'
    private val PIXEL_ON_CHAR  = '#'
  }

  private val rows: Int

  private val columns: Int

  var pixels = Array(
      size = rows,
      init = { Array(size = columns, init = { PIXEL_OFF_CHAR }) }
  )

  init {
    this.rows = rows
    this.columns = columns
  }

  val numberOfLitPixels: Int
    get() {
      return toString().count { char -> char == PIXEL_ON_CHAR }
    }

  fun applyInstruction(instruction: Instruction) {
    when (instruction) {
      is RectInstruction -> {
        for (row in 0 until instruction.rectHeight) {
          for (column in 0 until instruction.rectWidth) {
            pixels[row][column] = PIXEL_ON_CHAR
          }
        }
      }

      is RotateRowInstruction -> {
        val shiftedRow = Array(size = columns, init = { PIXEL_OFF_CHAR })

        (0 until columns).forEach { targetColumn ->
          val sourceColumn = (targetColumn - instruction.rotationAmount + columns) % columns
          shiftedRow[targetColumn] = pixels[instruction.rowNumber][sourceColumn]
        }

        pixels[instruction.rowNumber] = shiftedRow
      }

      is RotateColumnInstruction -> {
        val shiftedRow = Array(size = columns, init = { ' ' })

        (0 until rows).forEach { targetRow ->
          val sourceRow = (targetRow - instruction.rotationAmount + rows) % rows
          shiftedRow[targetRow] = pixels[sourceRow][instruction.columnNumber]
        }

        (0 until rows).forEach { row ->
          pixels[row][instruction.columnNumber] = shiftedRow[row]
        }
      }
    }
  }

  override fun toString(): String {
    return pixels.joinToString(transform = { it.joinToString(separator = "")}, separator = "\n")
  }

}