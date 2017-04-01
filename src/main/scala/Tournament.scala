import TheGame.Match

object Tournament {

  case object BracketType extends Enumeration {
    val WinnersBracket, LosersBracket = Value
  }

  case class Contestants(contestants: List[Participant])

  case class Seed(int: Int)

  case class RoundNumber(int: Int)

  case class Round(roundNumber: RoundNumber, matches: List[Match])

  case class Rounds(rounds: List[Round])

  case class Bracket()

  def createSeeds(participants: List[Participant]): List[Participant] = {
    val startingSeed = 1
    val seedList: List[Participant] = List.empty
    participants.foldLeft(startingSeed, seedList) {
      case ((seed, seedList), participant) =>
        (seed + 1, seedList :+ Participant(participant.emailAddress, participant.instructions, Seed(seed), participant.record))
    }._2
  }
}
