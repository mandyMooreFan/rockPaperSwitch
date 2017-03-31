import TheGame.Match

object TheTournament {

  case object Bracket extends Enumeration {
    val WinnersBracket, LosersBracket = Value
  }

  case class Round(matches: List[Match])

  case class Rounds(rounds: List[Round])
}
