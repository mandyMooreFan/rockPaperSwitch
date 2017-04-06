import java.util.UUID

import TheGame.Match
import Tournament.Seed

case class Participant(emailAddress: String, instructions: Seq[Instruction.Value], seed: Seed = Seed(0),
                       record: List[Match] = List.empty) {

  case class TeamName() {
    def apply(): String = ???
  }

}