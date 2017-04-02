import TheGame.Match

object Tournament {

  case object BracketType extends Enumeration {
    val WinnersBracket, LosersBracket = Value
  }

  case class Contestants(contestants: List[Participant])

  case class Seed(int: Int)

  case class Round(roundNumber: Int, matches: List[Match])

  case class Rounds(rounds: List[Round], bracketType: BracketType.Value)

  case class Bracket(participants: List[Participant], rounds: Rounds)

  def createSeeds(participants: List[Participant]): List[Participant] = {
    val startingSeed = 1
    val seedList: List[Participant] = List.empty
    participants.foldLeft(startingSeed, seedList) {
      case ((seed, seedList), participant) =>
        (seed + 1, seedList :+ Participant(participant.emailAddress, participant.instructions, Seed(seed), participant.record))
    }._2
  }

  def generateBracket(participants: List[Participant]): Round = {
    val firstRoundMatchCount = Math.ceil(participants.count(_ => true).toFloat / 2).toInt
    println(firstRoundMatchCount)
    val firstRoundMatches = (1 to firstRoundMatchCount).foldLeft(List(): List[Match]) {
      (matches, matchNumber) =>
        matches :+ Match(participants.head, participants.last, matchNumber, BracketType.WinnersBracket)
    }
    Round(1, firstRoundMatches)
  }

}
