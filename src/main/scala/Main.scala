import TheGame.Results

import scala.io.Source

object Main extends App {

  val fileName = "production.txt"
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
  val bracket = Tournament.generateFirstRound(seededParticipants)


  val winnerEmail = bracket.matches.map { amatch => (amatch.whoWon.emailAddress, amatch.whoWon.seed, amatch.findResults) }

  println(bracket.matches.map { amatch => (s"${amatch.player1.emailAddress} ${amatch.player1.seed}", s"${amatch.player2.emailAddress} ${amatch.player2.seed}") })
  println(winnerEmail)
}
