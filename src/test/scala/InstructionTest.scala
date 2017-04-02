import org.scalatest.{Matchers, WordSpec}


class InstructionTest extends WordSpec with Matchers {

  "Instruction" when {
    "when formated" should {
      "repeat the list 3 times" in {
        val stringInstruction = "Rock,Paper,Scissors,Rock,Rock"
        Instruction.formatInstructions(stringInstruction).mkString should startWith ("RockPaperScissorsRockRockRockPaperScissorsRockRockRockPaperScissorsRockRockRock")
      }
    }
  }
}