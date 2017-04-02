import java.util.UUID

import org.scalatest.{Matchers, WordSpec}

class ParticipantTest extends WordSpec with Matchers {

  "A Participant" when {
    "created" should {
      "also create a unique identifer" in {
        val participant = Participant("testEmail", List(Instruction.Rock))
        val participantId = participant.ParticipantId

        participant.ParticipantId should equal(participantId)
      }
    }
  }
}
