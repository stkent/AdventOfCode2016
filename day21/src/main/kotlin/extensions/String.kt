package extensions

import Instruction

fun String.applyInstruction(instruction: Instruction) : String {
  return instruction.apply(this)
}
