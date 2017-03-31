import java.util.UUID

import org.scalatest.{Matchers, WordSpec}

class ParticipantTest extends WordSpec with Matchers {

  "A Participant" when {
    "created" should {
      "also create a unique identifer" in {
        val test = Participant("testEmail", List(Instruction.Rock)).ParticipantId

        test should equal(UUID.randomUUID())
      }
    }
  }
}
