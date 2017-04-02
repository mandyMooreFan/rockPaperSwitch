import scala.util.Random

object Instruction extends Enumeration {
  val Rock, Paper, Scissors = Value

  def randomInstruction(): Instruction.Value = Random.nextInt(2) match {
    case 0 => Instruction.Rock
    case 1 => Instruction.Paper
    case 2 => Instruction.Scissors
  }

  def randomInstructionList(): List[Instruction.Value] = {
    val instructionList: List[Instruction.Value] = List()
    (1 to 15).foldLeft(instructionList) {
      (intrList, number) =>
        intrList :+ randomInstruction
    }
  }

  def formatInstructions(instructions: String): List[Instruction.Value] = {
    val startingList: List[Instruction.Value] = List.empty
    val instructionsSplit = instructions.split(",")
    val instructionList = instructionsSplit.foldLeft(startingList) {
      (theList, instruction) =>
        theList :+ Instruction.withName(instruction)
    }
    instructionList ::: instructionList ::: instructionList :::
      instructionList.reverse ::: instructionList.reverse ::: instructionList.reverse :::
      randomInstructionList
  }
}