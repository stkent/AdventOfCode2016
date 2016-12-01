data class Instruction(val rotationDirection: RotationDirection, val distanceToAdvance: Int) {

  constructor(instructionString: String) : this(
      rotationDirection = RotationDirection.parse(instructionString.substring(0, 1)),
      distanceToAdvance = instructionString.substring(1).toInt()
  )

}
