
object Instruction extends Enumeration {
  val Rock, Paper, Scissors = Value

  def formatInstructions(instructions: String): List[Instruction.Value] = {
    val startingList: List[Instruction.Value] = List.empty
    val instructionsSplit = instructions.split(",")
    val instructionList = instructionsSplit.foldLeft(startingList) {
      (theList, instruction) =>
        theList :+ Instruction.withName(instruction)
    }
    instructionList ::: instructionList ::: instructionList
  }
}