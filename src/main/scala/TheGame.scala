import Tournament.Bracket

object TheGame {

  case object Result extends Enumeration {
    val Win, Lose, Tie, NotPlayedYet = Value
  }

  case class Results(wins: Int, loses: Int, ties: Int)

  case class Game(player1Instruction: Instruction.Value, player2Instruction: Instruction.Value,
                  result: Result.Value = Result.NotPlayedYet)

  case class Match(player1: Participant, player2: Participant, bracket: Bracket.Value,
                   games: List[Game] = List.empty) {

    def findResults(): Results = {
      val wins = games.count(_.result == Result.Win)
      val loses = games.count(_.result == Result.Lose)
      val ties = games.count(_.result == Result.Tie)
      Results(wins, loses, ties)
    }

    def whoWon(): Participant = {
      if(findResults.wins == 3) player1 else player2
    }
  }

  def playGame(player1Instruction: Instruction.Value, player2Instruction: Instruction.Value): Game =
    (player1Instruction, player2Instruction) match {
      case (Instruction.Rock, Instruction.Rock) => Game(player1Instruction, player2Instruction, Result.Tie)
      case (Instruction.Rock, Instruction.Paper) => Game(player1Instruction, player2Instruction, Result.Lose)
      case (Instruction.Rock, Instruction.Scissors) => Game(player1Instruction, player2Instruction, Result.Win)
      case (Instruction.Paper, Instruction.Rock) => Game(player1Instruction, player2Instruction, Result.Win)
      case (Instruction.Paper, Instruction.Paper) => Game(player1Instruction, player2Instruction, Result.Tie)
      case (Instruction.Paper, Instruction.Scissors) => Game(player1Instruction, player2Instruction, Result.Lose)
      case (Instruction.Scissors, Instruction.Rock) => Game(player1Instruction, player2Instruction, Result.Lose)
      case (Instruction.Scissors, Instruction.Paper) => Game(player1Instruction, player2Instruction, Result.Win)
      case (Instruction.Scissors, Instruction.Scissors) => Game(player1Instruction, player2Instruction, Result.Tie)
    }

  def stalemate(player1Instructions: Seq[Instruction.Value], player2Instructions: Seq[Instruction.Value]): Boolean = {
    player1Instructions == player2Instructions
  }

  def playMatch(aMatch: Match): Match = {
    val instructionMap = aMatch.player1.instructions zip aMatch.player2.instructions
    val newResult = instructionMap
      .foldLeft(aMatch.games) {
        case (games, (player1Instruction, player2Instruction)) =>
          if (
            games.count(_.result == Result.Win) == 3 || games.count(_.result == Result.Lose) == 3
          ) games else {
            games :+ playGame(player1Instruction, player2Instruction)
          }
      }
    Match(aMatch.player1, aMatch.player2, aMatch.bracket, newResult)
  }
}
