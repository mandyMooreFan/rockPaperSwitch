import TheGame.{Game, Match, Result, Results}
import Tournament.Bracket
import org.scalatest.{Matchers, WordSpec}

class TheGameTest extends WordSpec with Matchers {

  trait Setup {
    val theGame = TheGame

    val instruction1String = "Rock,Rock,Rock,Rock,Rock"
    val instruction2String = "Paper,Rock,Rock,Rock,Rock"
    val instruction3String = "Rock,Rock,Rock,Rock,Rock"

    val instruction1 = Instruction.formatInstructions(instruction1String)
    val instruction2 = Instruction.formatInstructions(instruction2String)
    val instruction3 = Instruction.formatInstructions(instruction3String)

    val participant1 = Participant("test@gmail.com", instruction1)
    val participant2 = Participant("test2@gmail.com", instruction2)
  }

  "The game" when {

    "rockPaperScissors" should {
      "return the results of the match" in new Setup {
        theGame.playGame(Instruction.Rock, Instruction.Paper) should equal(Game(Instruction.Rock, Instruction.Paper, Result.Lose))
      }
    }

    "stalemate" should {
      "return true if the pattern matches" in new Setup {
        theGame.stalemate(instruction1, instruction3) should equal(true)
      }
      "return false if the pattern doesn't match" in new Setup {
        theGame.stalemate(instruction1, instruction2) should equal(false)
      }
    }

    "match" should {
      "be able to report back the outcome for each player" in new Setup {
        val theMatch = theGame.playMatch(Match(participant1, participant2, Bracket.WinnersBracket))

        theMatch should equal(
          Match(participant1, participant2, Bracket.WinnersBracket, List(
            Game(Instruction.Rock, Instruction.Paper, Result.Lose),
            Game(Instruction.Rock, Instruction.Rock, Result.Tie),
            Game(Instruction.Rock, Instruction.Rock, Result.Tie),
            Game(Instruction.Rock, Instruction.Rock, Result.Tie),
            Game(Instruction.Rock, Instruction.Rock, Result.Tie),
            Game(Instruction.Rock, Instruction.Paper, Result.Lose),
            Game(Instruction.Rock, Instruction.Rock, Result.Tie),
            Game(Instruction.Rock, Instruction.Rock, Result.Tie),
            Game(Instruction.Rock, Instruction.Rock, Result.Tie),
            Game(Instruction.Rock, Instruction.Rock, Result.Tie),
            Game(Instruction.Rock, Instruction.Paper, Result.Lose)
          )))

        theMatch.findResults() should equal(Results(0, 3, 8))
      }
    }
  }
}