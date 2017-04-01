import scala.io.Source

object Main extends App {

  val fileName = "test.txt"
  println(Source.fromResource(fileName))
  val fileSemiColonSplit = Source.fromResource(fileName).getLines.toList

  //Create participants from file.  Semi colon splits the email from the instructions.
  // Using commas to split each item in the instruction List
  val participants = fileSemiColonSplit.map {
    line =>
      Participant(
        line.substring(0, line.indexOf(";")),
        Instruction.formatInstructions(line.substring(line.indexOf(";") + 1))
      )
  }
  //Create Tournament seeds
  val seededParticipants = Tournament.createSeeds(participants)

  println(seededParticipants)
}
