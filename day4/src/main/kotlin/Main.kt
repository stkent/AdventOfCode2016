import java.io.File

class Main {
  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)
      
      val validRooms = inputFile
          .readLines()
          .map(::RoomCandidate)
          .filter(RoomCandidate::isARealRoom)

      println("Part 1: sum of sector IDs of real rooms = " + validRooms.sumBy(RoomCandidate::sectorId))
      
      validRooms
          .filter { it.getDecryptedRoomName() == "northpole object storage" }
          .forEach { println("Part 2: North Pole object storage room is in sector ${it.sectorId}") }
    }
  }
}
