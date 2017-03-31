
object TheGame {

  case object Result extends Enumeration {
    val Win, Lose, Tie = Value
  }

  case class Results(wins: Int, loses: Int, ties: Int)

  case class Game(player1: Participant, player2: Participant,
                  result: Result.Value)

  case class Match(games: List[Game]) {

    def findPlayer1Results(): Results = {
      val wins = games.count(_.result == Result.Win)
      val loses = games.count(_.result == Result.Lose)
      val ties = games.count(_.result == Result.Tie)
      Results(wins, loses, ties)
    }

    def findPlayer2Results(): Results = {
      val wins = games.count(_.result == Result.Lose)
      val loses = games.count(_.result == Result.Win)
      val ties = games.count(_.result == Result.Tie)
      Results(wins, loses, ties)
    }
  }

  def rockPaperScissors(player1Instruction: Instruction.Value, player2Instruction: Instruction.Value): Result.Value =
    (player1Instruction, player2Instruction) match {
      case (Instruction.Rock, Instruction.Rock) => Result.Tie
      case (Instruction.Rock, Instruction.Paper) => Result.Lose
      case (Instruction.Rock, Instruction.Scissors) => Result.Win
      case (Instruction.Paper, Instruction.Rock) => Result.Win
      case (Instruction.Paper, Instruction.Paper) => Result.Tie
      case (Instruction.Paper, Instruction.Scissors) => Result.Lose
      case (Instruction.Scissors, Instruction.Rock) => Result.Lose
      case (Instruction.Scissors, Instruction.Paper) => Result.Win
      case (Instruction.Scissors, Instruction.Scissors) => Result.Tie
    }

  def stalemate(player1Instructions: List[Instruction.Value], player2Instructions: List[Instruction.Value]): Boolean = {
    player1Instructions == player2Instructions
  }
}
