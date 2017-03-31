import java.util.UUID

import TheTournament.Bracket


case class Participant(emailAddress: String, instructions: List[Instruction.Value], record: List[TheGame.Game],
                       bracket: Bracket.Value) {

  case class ParticipantId() {
    def apply(): UUID = UUID.randomUUID()
  }

}