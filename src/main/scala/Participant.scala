import java.util.UUID

import TheGame.Match
import TheTournament.Bracket


case class Participant(emailAddress: String, instructions: List[Instruction.Value], record: List[Match],
                       bracket: Bracket.Value) {

  case class ParticipantId() {
    def apply(): UUID = UUID.randomUUID()
  }

}