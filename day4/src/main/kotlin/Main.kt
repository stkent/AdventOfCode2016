import java.io.File

class Main {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)

      val realRooms = inputFile
          .readLines()
          .map(::RoomCandidate)
          .filter(RoomCandidate::isARealRoom)

      println("Part 1: ${realRooms.sumBy(RoomCandidate::sectorId)}")

      realRooms
          .filter { it.getDecryptedRoomName() == "northpole object storage" }
          .forEach { println("Part 2: ${it.sectorId}") }
    }
  }

}
