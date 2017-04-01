import Tournament.Seed
import org.scalatest.{Matchers, WordSpec}

class TournamentTest extends WordSpec with Matchers {

  trait Setup {
    val theTournament = Tournament

    val instruction1 = List(Instruction.Rock)
    val participants = List(
      Participant("test@gmail.com", instruction1),
      Participant("test2@gmail.com", instruction1),
      Participant("test3@gmail.com", instruction1)
    )
  }

  "In a Tournament" when {

    "participants have been found.  Create seed" should {
      "assign each participant a seed." in new Setup {
        val seededList: List[Participant] = theTournament.createSeeds(participants)
        seededList.head should equal(Participant("test@gmail.com", instruction1, Seed(1), List.empty))
        seededList(1) should equal(Participant("test2@gmail.com", instruction1, Seed(2), List.empty))
        seededList(2) should equal(Participant("test3@gmail.com", instruction1, Seed(3), List.empty))
      }
    }

  }
}
